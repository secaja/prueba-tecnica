package com.prueba.tecnica.service.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CharacterItem {
    @JsonProperty("resourceURI")
    private String resourceURI;

    @JsonProperty("name")
    private String name;

    @JsonProperty("role")
    private String role;
}
