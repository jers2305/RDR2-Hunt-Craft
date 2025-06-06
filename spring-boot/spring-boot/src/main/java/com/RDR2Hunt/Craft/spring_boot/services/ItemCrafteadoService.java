package com.RDR2Hunt.Craft.spring_boot.services;

import com.RDR2Hunt.Craft.spring_boot.models.ItemCrafteado;
import com.RDR2Hunt.Craft.spring_boot.repositories.ItemCrafteadoRepository;
import com.RDR2Hunt.Craft.spring_boot.repositories.ItemFavoritoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemCrafteadoService {
    private final ItemCrafteadoRepository repo;

    public List<ItemCrafteado> getByUsuario(Long usuarioId) {
        return repo.findByUsuarioId(usuarioId);
    }

    public ItemCrafteado save(ItemCrafteado favorito) {
        return repo.save(favorito);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
