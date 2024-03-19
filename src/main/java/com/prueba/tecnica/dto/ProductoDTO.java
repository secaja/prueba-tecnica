package com.prueba.tecnica.dto;

import com.prueba.tecnica.models.Producto;
import lombok.Data;

@Data
public class ProductoDTO {
    private Long id;
    private String descripcion;
    private String modelo;
    private int cantidadEnBodega;
    private double valorDeVenta;


}