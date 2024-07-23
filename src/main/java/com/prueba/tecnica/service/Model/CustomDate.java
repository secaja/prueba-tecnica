package com.prueba.tecnica.service.Model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Data
public class CustomDate {
    private String type;
    private java.util.Date date;

}
