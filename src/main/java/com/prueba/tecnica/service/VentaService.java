package com.prueba.tecnica.service;

import com.prueba.tecnica.models.Producto;
import com.prueba.tecnica.models.RegistroInventario;
import com.prueba.tecnica.models.TipoTransaccion;
import com.prueba.tecnica.repository.ProductoRepository;
import com.prueba.tecnica.repository.RegistroInventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VentaService {
    private final ProductoRepository productoRepository;
    private final RegistroInventarioRepository registroInventarioRepository;

    @Autowired
    public VentaService(ProductoRepository productoRepository, RegistroInventarioRepository registroInventarioRepository) {
        this.productoRepository = productoRepository;
        this.registroInventarioRepository = registroInventarioRepository;
    }

    public void registrarVenta(Long productoId, int cantidad){
        Producto producto = productoRepository.findById(productoId).orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        int cantidadActual = producto.getCantidadEnBodega();

        if (cantidadActual < cantidad){
            throw new RuntimeException("No hay suficientes productos en inventario");
        }

        producto.setCantidadEnBodega(cantidadActual - cantidad);
        productoRepository.save(producto);

        RegistroInventario registro = new RegistroInventario();
        registro.setProducto(producto);
        registro.setTipoTransaccion(TipoTransaccion.VENTA);
        registro.setCantidad(cantidad);
        registroInventarioRepository.save(registro);

    }
}
