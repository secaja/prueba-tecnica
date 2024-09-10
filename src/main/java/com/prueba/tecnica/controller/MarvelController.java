package com.prueba.tecnica.controller;

import com.prueba.tecnica.models.Comic;
import com.prueba.tecnica.service.MarvelAPIClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/marvel")
public class MarvelController {

    @Autowired
    private final MarvelAPIClient  marvelAPIClient;

    public MarvelController(MarvelAPIClient marvelAPIClient) {
        this.marvelAPIClient = marvelAPIClient;
    }

    @GetMapping("/comics")
    public ResponseEntity<List<Comic>> obtenerComics() {
        List<Comic> comics = marvelAPIClient.obtenerListaComics();
        return ResponseEntity.ok(comics);
    }
}
