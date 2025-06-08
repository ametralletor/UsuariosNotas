package com.proyecto.NotasUsuarios.repository;


import com.proyecto.NotasUsuarios.model.Notas;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotaRepository extends JpaRepository<Notas, Long> {
    List<Notas> findByUsuarioId(Long usuarioId, String sort);
    
}
