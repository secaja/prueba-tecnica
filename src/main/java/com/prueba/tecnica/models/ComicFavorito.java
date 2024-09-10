package com.prueba.tecnica.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class ComicFavorito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario  usuario;

    private String comicId;

    private String title;

    private String description;

    private String urlImagen;
}
