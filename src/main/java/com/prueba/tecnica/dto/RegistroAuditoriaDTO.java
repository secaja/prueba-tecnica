package com.prueba.tecnica.dto;

import lombok.Data;

import java.util.Date;

@Data
public class RegistroAuditoriaDTO {
    private Long id;
    private String accionRealizada;
    private Date fechaRegistro;
    private UsuarioDTO usuario;
}