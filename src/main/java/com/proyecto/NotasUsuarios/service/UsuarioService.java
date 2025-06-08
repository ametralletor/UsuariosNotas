package com.proyecto.NotasUsuarios.service;

import com.proyecto.NotasUsuarios.model.Usuario;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface UsuarioService extends CrudService<Usuario, Long> {

    List<Usuario> searchUsuario(String nombre, String email, Sort sort);
    boolean existsByEmail(String email);

    Optional<Usuario> findByEmail(String email);
}
