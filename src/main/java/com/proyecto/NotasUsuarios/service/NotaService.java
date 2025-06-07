package com.proyecto.NotasUsuarios.service;

import java.util.List;

import com.proyecto.NotasUsuarios.model.Notas;

public interface NotaService extends CrudService<Notas, Long> {
    
    
    List<Notas> getNotasByUsuarioId(Long usuarioId, String order);


}
