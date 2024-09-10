package com.prueba.tecnica.controller;

import com.prueba.tecnica.models.ComicFavorito;
import com.prueba.tecnica.service.ComicFavoritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ComicFavoriteController {
    @Autowired
    private ComicFavoritoService comicFavoritoService;

    @PostMapping("/guardar/{usuarioId}")
    public ResponseEntity<ComicFavorito> guardarComicFavorito(
            @PathVariable Long usuarioId,
            @RequestBody ComicFavorito comicFavorito) {
        return ResponseEntity.ok(comicFavoritoService.markAsFavorite(usuarioId, comicFavorito));
    }

    @GetMapping("/user/{usuarioId}")
    public List<ComicFavorito> getFavoriteByUserId(@PathVariable Long usuarioId){
        return comicFavoritoService.getFavoritesByUserId(usuarioId);
    }

}
