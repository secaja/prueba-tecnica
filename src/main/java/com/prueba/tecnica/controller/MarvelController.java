package com.prueba.tecnica.controller;

import com.prueba.tecnica.models.Comic;
import com.prueba.tecnica.service.MarvelAPIClient;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Comic> obtenerComics() {
        return marvelAPIClient.obtenerListaComics();
    }
}
