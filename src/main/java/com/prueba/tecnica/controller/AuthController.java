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

    private PasswordEncoder passwordEncoder;

    private RoleRepository roleRepository;

    private UsuarioRepository usuarioRepository;

    private JwtGenerator jwtGenerator;

    @Autowired

    public AuthController(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder,
                          RoleRepository roleRepository, UsuarioRepository usuarioRepository, JwtGenerator jwtGenerator) {

        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.usuarioRepository = usuarioRepository;
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping("register")
    public ResponseEntity<String> registrar(@RequestBody DtoRegistro registro) {
        if (usuarioRepository.existsByUsername(registro.getUsername())) {
            return new ResponseEntity<>("El usuario ya se encuentra registrado", HttpStatus.BAD_REQUEST);
        }

        Usuario usuario = new Usuario();
        usuario.setUsername(registro.getUsername());
        usuario.setPassword(passwordEncoder.encode(registro.getPassword()));

        // Verificar si se proporcionó un roleName en la solicitud
        if (registro.getRoleName() != null) {
            // Buscar el rol por nombre en la base de datos
            Role role = roleRepository.findByName(registro.getRoleName()).orElse(null);
            // Verificar si se encontró el rol
            if (role == null) {
                return new ResponseEntity<>("El rol especificado no existe", HttpStatus.BAD_REQUEST);
            }
            usuario.setRoles(Collections.singletonList(role));
        }

        usuarioRepository.save(usuario);
        return new ResponseEntity<>("Registro exitoso!", HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateUser(@RequestBody DtoRegistro registro){
        Optional<Usuario> optionalUsuario = usuarioRepository.findByUsername(registro.getUsername());
        if (optionalUsuario.isEmpty()){
            return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
        }

        Usuario usuario = optionalUsuario.get();
        usuario.setPassword(passwordEncoder.encode(registro.getPassword()));
        Optional<Role> optionalRole = roleRepository.findByName(registro.getRoleName());

        if (optionalRole.isEmpty()){
            return new ResponseEntity<>("Rol no encontrado", HttpStatus.NOT_FOUND);
        }

        Role role = optionalRole.get();

        List<Role> roles = usuario.getRoles();
        roles.clear();
        roles.add(role);

        usuarioRepository.save(usuario);

        return new ResponseEntity<>("Actualizacion exitosa", HttpStatus.OK);
    }


    @PostMapping("login")
    public ResponseEntity<DtoAuthResponse> login(@RequestBody DtoLogin login){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new ResponseEntity<>(new DtoAuthResponse(token), HttpStatus.OK);

    }

    @DeleteMapping("deleteUser/{id}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable Long id) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);

        if (optionalUsuario.isEmpty()) {
            return new ResponseEntity<>("Usuario no encontrado con el ID: " + id, HttpStatus.NOT_FOUND);
        }

        Usuario usuario = optionalUsuario.get();
        usuario.getRoles().clear();

        usuarioRepository.deleteById(id);
        return new ResponseEntity<>("Usuario eliminado correctamente", HttpStatus.OK);
    }

}
