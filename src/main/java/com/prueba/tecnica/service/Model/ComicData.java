package com.prueba.tecnica.service.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ComicData {
    @JsonProperty("offset")
    private int offset;
    @JsonProperty("limit")
    private int limit;
    @JsonProperty("total")
    private int total;
    @JsonProperty("count")
    private int count;
    @JsonProperty("results")
    private List<Comic> results;
}
