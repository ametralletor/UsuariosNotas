package com.proyecto.NotasUsuarios.service;

import com.proyecto.NotasUsuarios.model.Usuario;
import com.proyecto.NotasUsuarios.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UsuarioServiceImpl extends AbstractCrudService<Usuario, Long> implements UsuarioService {

    private final UsuarioRepository UsuarioRepo;
    private final PasswordEncoder passwordEncoder;

    public UsuarioServiceImpl(JpaRepository<Usuario, Long> repo, UsuarioRepository usuarioRepo, PasswordEncoder passwordEncoder) {
        super(usuarioRepo);
        UsuarioRepo = usuarioRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean existsByEmail(String email) {
        return UsuarioRepo.existsByEmail(email);
    }

    @Override
    public Usuario save(Usuario usuario){
        usuario.setContrasenna(passwordEncoder.encode(usuario.getContrasenna()));
        return super.save(usuario);
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
