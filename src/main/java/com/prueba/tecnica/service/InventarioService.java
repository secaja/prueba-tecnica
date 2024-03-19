package com.prueba.tecnica.service;

import com.prueba.tecnica.models.Producto;
import com.prueba.tecnica.models.RegistroInventario;
import com.prueba.tecnica.models.TipoTransaccion;
import com.prueba.tecnica.repository.ProductoRepository;
import com.prueba.tecnica.repository.RegistroInventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventarioService {

    private final RegistroInventarioRepository registroInventarioRepository;
    private final ProductoRepository productoRepository;

    @Autowired
    public InventarioService(RegistroInventarioRepository registroInventarioRepository, ProductoRepository productoRepository) {
        this.registroInventarioRepository = registroInventarioRepository;
        this.productoRepository = productoRepository;
    }

    public void registrarEntradaInventario(Long productoId, int cantidad) {
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + productoId));

        producto.setCantidadEnBodega(producto.getCantidadEnBodega() + cantidad);
        productoRepository.save(producto);

        RegistroInventario registro = new RegistroInventario();
        registro.setProducto(producto);
        registro.setTipoTransaccion(TipoTransaccion.ENTRADA);
        registro.setCantidad(cantidad);
        registroInventarioRepository.save(registro);
    }

}
