package com.proyecto.NotasUsuarios.repository;

import com.proyecto.NotasUsuarios.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.data.domain.Sort;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    boolean existsByEmail(String email);

    List<Usuario> findByNombreOrEmail(
            String nombre,
            String email,
            Sort sort
    );
}
