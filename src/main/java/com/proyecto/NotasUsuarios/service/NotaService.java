package com.proyecto.NotasUsuarios.service;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.proyecto.NotasUsuarios.model.Notas;

public interface NotaService extends CrudService<Notas, Long> {
    List<Notas> getNotasByUsuarioId(Long usuarioId, String sort);
}

