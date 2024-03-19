package com.prueba.tecnica.dto;

import com.prueba.tecnica.models.Role;
import lombok.Data;

@Data
public class UsuarioDTO {

    private Long id;
    private String username;
    private String password;
    private Role rol;

}
