package com.proyecto.NotasUsuarios.controller;

import com.proyecto.NotasUsuarios.model.*;
import com.proyecto.NotasUsuarios.service.NotaService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class NotaController {
    private final NotaService notaService;

    public NotaController(NotaService notaService) {
        this.notaService = notaService;
    }

    @GetMapping
    public ResponseEntity<List<Notas>> getAllNotas() {
        return ResponseEntity.ok(notaService.getAll());
    }

    @GetMapping
    public ResponseEntity<List<Notas>> getNotasByUsuarioId(
        @RequestParam @Positive Long usuarioId, 
        @RequestParam(defaultValue = "asc") String order) {
                return ResponseEntity.ok(notaService.getNotasByUsuarioId(usuarioId, order));       
    }
    
    @GetMapping("/{id}")
    public Notas getNotaById(@PathVariable Long id) {
        return notaService.getById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nota con ID " + id + " no encontrada")
        );
    }

    @PostMapping
    public ResponseEntity<Notas> createNota(@RequestParam Long usuarioId,@RequestBody @Valid Notas nota) {
        nota.setFechaCreacion(LocalDateTime.now());
        Notas createdNota = notaService.save(nota);
        return ResponseEntity.created(URI.create("/api/v1/notas/" + createdNota.getId())).body(createdNota);
    }

    @PutMapping("/notas/{id}")
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

}
