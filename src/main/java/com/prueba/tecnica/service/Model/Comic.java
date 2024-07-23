package com.prueba.tecnica.service.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data

public class Comic {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("digitalId")
    private int digitalId;

    @JsonProperty("title")
    private String title;

    @JsonProperty("issueNumber")
    private double issueNumber;

    @JsonProperty("variantDescription")
    private String variantDescription;

    @JsonProperty("description")
    private String description;

    @JsonProperty("modified")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private Date modified;

    @JsonProperty("isbn")
    private String isbn;

    @JsonProperty("upc")
    private String upc;

    @JsonProperty("diamondCode")
    private String diamondCode;

    @JsonProperty("ean")
    private String ean;

    @JsonProperty("issn")
    private String issn;

    @JsonProperty("format")
    private String format;

    @JsonProperty("pageCount")
    private int pageCount;

    @JsonProperty("textObjects")
    private List<TextObject> textObjects;

    @JsonProperty("resourceURI")
    private String resourceURI;

    @JsonProperty("urls")
    private List<Url> urls;

    @JsonProperty("series")
    private Series series;

    @JsonProperty("variants")
    private List<Variant> variants;

    @JsonProperty("collections")
    private List<Collection> collections;

    @JsonProperty("collectedIssues")
    private List<CollectedIssue> collectedIssues;

    /*@JsonProperty("dates")
    private List<CustomDate> customDates;*/

    @JsonProperty("prices")
    private List<Price> prices;

    @JsonProperty("thumbnail")
    private Image thumbnail;

    @JsonProperty("images")
    private List<Image> images;

    @JsonProperty("creators")
    private CreatorList creators;

    @JsonProperty("characters")
    private CharacterList characters;

    @JsonProperty("stories")
    private StoryList stories;

    @JsonProperty("events")
    private EventList events;
}
