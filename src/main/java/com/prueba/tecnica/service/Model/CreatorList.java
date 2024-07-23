package com.prueba.tecnica.service.Model;

import lombok.Data;

import java.util.List;

@Data
public class CreatorList {
    private int available;
    private int returned;
    private String collectionURI;
    private List<CreatorItem> items;
}
