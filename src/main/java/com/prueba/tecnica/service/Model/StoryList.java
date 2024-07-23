package com.prueba.tecnica.service.Model;

import lombok.Data;

import java.util.List;

@Data
public class StoryList {
    private int available;
    private int returned;
    private String collectionURI;
    private List<StoryItem> items;
}
