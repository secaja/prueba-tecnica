package com.prueba.tecnica.controller;

import com.prueba.tecnica.dto.ProductoDTO;
import com.prueba.tecnica.models.Producto;
import com.prueba.tecnica.repository.ProductoRepository;
import com.prueba.tecnica.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Autowired ProductoRepository productoRepository;

    @GetMapping
    public List<Producto> ListaProductos(){
        return productoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerProductoPorId(@PathVariable("id") Long id) {
        Optional<Producto> productoOptional = productoService.buscarProductoPorId(id);
        if (productoOptional.isPresent()) {
            return ResponseEntity.ok(productoOptional.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
        }
    }

    @PostMapping("/agregar")
    public ResponseEntity<ProductoDTO> agregarProducto(@RequestBody ProductoDTO producto) {
        ProductoDTO nuevoProducto = productoService.agregarProducto(producto);
        return new ResponseEntity<>(nuevoProducto, HttpStatus.CREATED);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable("id") Long id, @RequestBody Producto productoActualizado) {
        Producto productoExistente = productoService.obtenerProductoPorId(id);
        if (productoExistente == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        productoActualizado.setId(id); // Asegura que el ID del producto actualizado sea el mismo que el del producto existente
        Producto productoActualizadoGuardado = productoService.guardarProducto(productoActualizado);

        return new ResponseEntity<>(productoActualizadoGuardado, HttpStatus.OK);
    }

    public ProductoController(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarProducto(@PathVariable Long id) {
        if (!productoRepository.existsById(id)) {
            return new ResponseEntity<>("Producto no encontrado", HttpStatus.NOT_FOUND);
        }

        productoRepository.deleteById(id);
        return new ResponseEntity<>("Producto eliminado exitosamente", HttpStatus.OK);
    }

}
