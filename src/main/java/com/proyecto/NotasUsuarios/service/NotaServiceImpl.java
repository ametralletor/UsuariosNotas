package com.proyecto.NotasUsuarios.service;

import com.proyecto.NotasUsuarios.model.Notas;
import com.proyecto.NotasUsuarios.repository.NotaRepository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class NotaServiceImpl extends AbstractCrudService<Notas, Long> implements NotaService {

    private final NotaRepository notaRepo;
    //private final UsuarioService usuarioService;

    public NotaServiceImpl(NotaRepository repo //,UsuarioService usuarioService
    ){
        super(repo);
        this.notaRepo = repo;
        //this.usuarioService = usuarioService;
    }



    @Override
    public List<Notas> getNotasByUsuarioId(Long usuarioId, String order) {
        Sort sort = Sort.by("fechaCreacion");
        sort = order.equalsIgnoreCase("desc") ? sort.descending() : sort.ascending();
        return notaRepo.findByUsuarioId(usuarioId, sort);
    }
/* 
    @Override
    public Notas save(Notas nota) {
        if(nota.getUsuario() == null || nota.getUsuario().getId() == null) {
            throw new IllegalArgumentException("El usuario de la nota no puede ser ninguno o no tener ID.");
        }
        return super.save(nota);
    }
*/

    
}
