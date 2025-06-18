package com.RDR2Hunt.Craft.spring_boot.services;

import com.RDR2Hunt.Craft.spring_boot.models.ItemFavorito;
import com.RDR2Hunt.Craft.spring_boot.repositories.ItemFavoritoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemFavoritoService {
    private  ItemFavoritoRepository repo;

    public List<ItemFavorito> getByUsuario(Long usuarioId) {
        return repo.findByUsuarioId(usuarioId);
    }

    public ItemFavorito save(ItemFavorito favorito) {
        return repo.save(favorito);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
