package com.proyecto.NotasUsuarios.controller;

import java.util.List;

import com.proyecto.NotasUsuarios.model.Usuario;
import com.proyecto.NotasUsuarios.service.UsuarioService;
import jakarta.validation.Valid;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v2")
public class UsuarioController2 {
   
    
    private final PasswordEncoder passwordEncoder;
    private final UsuarioService usuarioService;
    public UsuarioController2(UsuarioService usuarioService, PasswordEncoder passwordEncoder) {
        this.usuarioService = usuarioService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/sign-in")
    public ResponseEntity<Usuario> signIn(@RequestBody RequestSign req) {
        Usuario usuario = usuarioService.findByEmail(req.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado con el email: " + req.getEmail()));
                
        if (!passwordEncoder.matches(req.getContrasenna(), usuario.getContrasenna())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Contraseña incorrecta");
        }

        return ResponseEntity.ok(usuario);
    }
}
