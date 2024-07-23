package com.prueba.tecnica.dto;

import com.prueba.tecnica.models.Role;
import lombok.Data;

@Data
public class DtoRegistro {

    private String username;
    private String password;
    private String roleName;
    private String correo;
    private String documento;

}
