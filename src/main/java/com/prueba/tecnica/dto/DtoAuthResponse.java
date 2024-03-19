package com.prueba.tecnica.dto;

import lombok.Data;

@Data
public class DtoAuthResponse {
    private String accesstoken;
    private String tokenType = "Bearer";

    public DtoAuthResponse(String accesstoken) {
        this.accesstoken = accesstoken;

    }
}
