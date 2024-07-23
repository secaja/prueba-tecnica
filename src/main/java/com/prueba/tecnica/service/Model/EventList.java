package com.prueba.tecnica.service.Model;

import lombok.Data;

import java.util.List;

@Data
public class EventList {

    private int available;
    private int returned;
    private String collectionURI;
    private List<EventItem> items;
}
