package com.prueba.tecnica.service.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class CharacterList {

    @JsonProperty("available")
    private int available;

    @JsonProperty("returned")
    private int returned;

    @JsonProperty("collectionURI")
    private String collectionURI;

    @JsonProperty("items")
    private List<CharacterItem> items;
}
