package com.prueba.tecnica.service;

import com.prueba.tecnica.dto.ProductoDTO;
import com.prueba.tecnica.models.Producto;
import com.prueba.tecnica.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Autowired
    private ProductoRepository productoRepository;

    public ProductoDTO agregarProducto(ProductoDTO productoDTO) {
        Producto producto = new Producto();
        producto.setCantidadEnBodega(productoDTO.getCantidadEnBodega());
        producto.setDescripcion(productoDTO.getDescripcion());
        producto.setModelo(productoDTO.getModelo());
        producto.setValorDeVenta(productoDTO.getValorDeVenta());

        Producto nuevoProducto = productoRepository.save(producto);

        ProductoDTO nuevoProductoDTO = new ProductoDTO();
        nuevoProductoDTO.setCantidadEnBodega(nuevoProducto.getCantidadEnBodega());
        nuevoProductoDTO.setDescripcion(nuevoProducto.getDescripcion());
        nuevoProductoDTO.setModelo(nuevoProducto.getModelo());
        nuevoProductoDTO.setValorDeVenta(nuevoProducto.getValorDeVenta());

        return nuevoProductoDTO;
    }

    public Producto obtenerProductoPorId(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    public Producto guardarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public Optional<Producto> buscarProductoPorId(Long id) {
        return productoRepository.findById(id);
    }
}
