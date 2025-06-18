package com.RDR2Hunt.Craft.spring_boot.services;

import com.RDR2Hunt.Craft.spring_boot.models.ItemFavorito;
import com.RDR2Hunt.Craft.spring_boot.models.MaterialConseguido;
import com.RDR2Hunt.Craft.spring_boot.repositories.MaterialConseguidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MaterialConseguidoService {
    private MaterialConseguidoRepository repo;

    public List<MaterialConseguido> getByUsuario(Long usuarioId) {
        return repo.findByUsuarioId(usuarioId);
    }

    public MaterialConseguido save(MaterialConseguido favorito) {
        return repo.save(favorito);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
