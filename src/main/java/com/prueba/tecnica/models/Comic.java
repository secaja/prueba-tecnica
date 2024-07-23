package com.prueba.tecnica.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comic {

    private Long id;
    private String title;
    private String description;
    private Date modified;
    private String urlImagen;

}
