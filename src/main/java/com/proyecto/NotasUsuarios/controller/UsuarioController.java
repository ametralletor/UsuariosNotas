package com.proyecto.NotasUsuarios.controller;

import java.util.List;

import com.proyecto.NotasUsuarios.model.Usuario;
import com.proyecto.NotasUsuarios.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService){this.usuarioService = usuarioService;}

    @GetMapping
    public ResponseEntity<List<Usuario>> getAllUsuarios(){return ResponseEntity.ok(usuarioService.getAll());}

    @GetMapping("/{id}")
    public Usuario getUsuarioById(@PathVariable Long id){
        return usuarioService.getById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "El Usuario de Id "+id+" No fue encontrado")
        );
    }

    @PostMapping
    public ResponseEntity<Usuario> createUsuario(@Valid @RequestBody Usuario usuario){
        if (usuarioService.existsByEmail(usuario.getEmail())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Este Email ya esta Regitrado");
        }
        Usuario created = usuarioService.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> updateUsuario(
            @PathVariable("id") Long id,
            @RequestBody @Valid Usuario usuario){
        if (!usuarioService.getById(id).isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El Usuario de Id "+id+" No fue encontrado");
        }
        usuario.setId(id);
        Usuario updateUsuario = usuarioService.update(id, usuario);
        return ResponseEntity.ok(updateUsuario);
    }

    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable Long id){usuarioService.deleteById(id);}

}
