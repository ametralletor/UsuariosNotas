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

    public NotaServiceImpl(NotaRepository repo) {
        super(repo);
        this.notaRepo = repo;
    }

    @Override
    public List<Notas> getNotasByUsuarioId(Long usuarioId, String order) {
        Sort sort = Sort.by("fechaCreacion");
        sort = order.equalsIgnoreCase("desc") ? sort.descending() : sort.ascending();
        return notaRepo.findByUsuarioId(usuarioId, sort);
    }




    
}
