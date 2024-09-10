package com.prueba.tecnica.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComicFavoritoDTO {

    private String comicId;
    private String title;
    private String description;
    private String urlImagen;
}
