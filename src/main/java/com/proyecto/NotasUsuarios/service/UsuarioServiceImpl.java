package com.proyecto.NotasUsuarios.service;

import com.proyecto.NotasUsuarios.model.Usuario;
import com.proyecto.NotasUsuarios.repository.UsuarioRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UsuarioServiceImpl extends AbstractCrudService<Usuario, Long> implements UsuarioService {

    private final UsuarioRepository UsuarioRepo;

    public UsuarioServiceImpl(JpaRepository<Usuario, Long> repo, UsuarioRepository usuarioRepo) {
        super(usuarioRepo);
        UsuarioRepo = usuarioRepo;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Usuario> searchUsuario(String nombre, String email, Sort sort){
        String nombreFilter = (nombre == null || nombre.isEmpty()) ? null : "%" + nombre + "%";
        String emailFilter = (email == null || email.isEmpty()) ? null : "%" + email + "%";
        return UsuarioRepo.findByNombreOrEmail(
                nombreFilter, emailFilter, sort
        );
    }
}
