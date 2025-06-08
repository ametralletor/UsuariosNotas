package com.proyecto.NotasUsuarios.controller;

import com.proyecto.NotasUsuarios.model.*;
import com.proyecto.NotasUsuarios.service.NotaService;
import com.proyecto.NotasUsuarios.service.UsuarioService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;



@RestController
@RequestMapping("/api/v1/notas")
@Validated
public class NotaController {
    private final NotaService notaService;
    private final UsuarioService usuarioService;

    public NotaController(NotaService notaService, UsuarioService usuarioService) {
        this.notaService = notaService;
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<Notas>> getAllNotas(
        @RequestParam(required = false) @Positive Long usuarioId, 
        @RequestParam(defaultValue = "asc") String order) {
        if (order == null || (!order.equalsIgnoreCase("asc") && !order.equalsIgnoreCase("desc"))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Orden debe ser 'asc' o 'desc'");
            
        }

        Sort sort = order.equalsIgnoreCase("asc") ? Sort.by("fechaCreacion").ascending() : Sort.by("fechaCreacion").descending();
        return ResponseEntity.ok(notaService.getNotasByUsuarioId(usuarioId, sort));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Notas> getNotaById(@PathVariable @Positive Long id) {
        return notaService.getById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nota no encontrada"));
    }

    @PostMapping
    public ResponseEntity<Notas> createNota(@RequestParam @Positive Long usuarioId,@RequestBody @Valid Notas nota) {
        Usuario usuario = usuarioService.getById(usuarioId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario con ID " + usuarioId + " no encontrado"));
        nota.setUsuario(usuario);
        nota.setFechaCreacion(LocalDateTime.now());
        Notas createdNota = notaService.save(nota);
        return ResponseEntity.created(URI.create("/api/v1/notas/" + createdNota.getId())).body(createdNota);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Notas> updateNota(
        @PathVariable("id") Long id, 
        @RequestBody @Valid Notas nota) {

            if (!notaService.getById(id).isPresent()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nota con ID " + id + " no encontrada");
                
            }
        nota.setId(id);
        Notas updatedNota = notaService.update(id, nota);
        return ResponseEntity.ok(updatedNota);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@Positive @PathVariable Long id) {
                return notaService.getById(id)
                .map(existing -> {
                    notaService.deleteById(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
    }




