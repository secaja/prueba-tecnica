package com.prueba.tecnica.controller;

import com.prueba.tecnica.dto.DtoAuthResponse;
import com.prueba.tecnica.dto.DtoLogin;
import com.prueba.tecnica.dto.DtoRegistro;
import com.prueba.tecnica.models.Role;
import com.prueba.tecnica.models.Usuario;
import com.prueba.tecnica.repository.RoleRepository;
import com.prueba.tecnica.repository.UsuarioRepository;
import com.prueba.tecnica.security.JwtGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth/")
public class AuthController {

    private AuthenticationManager authenticationManager;

    private JwtGenerator jwtGenerator;

    @Autowired
    public AuthController(
            AuthenticationManager authenticationManager,
            JwtGenerator jwtGenerator) {

        this.authenticationManager = authenticationManager;
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping("login")
    public ResponseEntity<DtoAuthResponse> login(@RequestBody DtoLogin login){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new ResponseEntity<>(new DtoAuthResponse(token), HttpStatus.OK);

    }

}
