package com.prueba.tecnica.service.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComicDataWrapper {

    @JsonProperty("code")
    private int code;

    @JsonProperty("status")
    private String status;

    @JsonProperty("copyright")
    private String copyright;

    @JsonProperty("attributionText")
    private String attributionText;

    @JsonProperty("attributionHTML")
    private String attributionHTML;

    @JsonProperty("data")
    private ComicData comicData;

    @JsonProperty("etag")
    private String etag;
}
