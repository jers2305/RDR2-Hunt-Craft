package com.RDR2Hunt.Craft.spring_boot.services;

import com.RDR2Hunt.Craft.spring_boot.models.OutfitFavorito;
import com.RDR2Hunt.Craft.spring_boot.repositories.OutfitFavoritoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OutfitFavoritoService {
    private final OutfitFavoritoRepository repo;

    public List<OutfitFavorito> getByUsuario(Long usuarioId) {
        return repo.findByUsuarioId(usuarioId);
    }

    public OutfitFavorito save(OutfitFavorito favorito) {
        return repo.save(favorito);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
