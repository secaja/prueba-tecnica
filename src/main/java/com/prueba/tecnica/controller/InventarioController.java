package com.prueba.tecnica.controller;

import com.prueba.tecnica.dto.RegistroInventarioDTO;
import com.prueba.tecnica.dto.VentaDTO;
import com.prueba.tecnica.service.InventarioService;
import com.prueba.tecnica.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/inventario")
public class InventarioController {

    private final InventarioService inventarioService;
    private final VentaService ventaService;

    @Autowired
    public InventarioController(InventarioService inventarioService, VentaService ventaService) {
        this.inventarioService = inventarioService;
        this.ventaService = ventaService;
    }

    @PostMapping("/entrada")
    public ResponseEntity<String> registrarEntradaInventario(@RequestBody RegistroInventarioDTO request) {
        inventarioService.registrarEntradaInventario(request.getProductoId(), request.getCantidad());
        return ResponseEntity.status(HttpStatus.CREATED).body("Entrada de inventario registrada correctamente");
    }

    @PostMapping("/venta")
    public ResponseEntity<String> registrarVenta(@RequestBody VentaDTO ventaDTO){
        try {
            ventaService.registrarVenta(ventaDTO.getProductoId(), ventaDTO.getCantidad());
            return ResponseEntity.status(HttpStatus.CREATED).body("Venta realizada con exito!");
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
