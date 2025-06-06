package com.RDR2Hunt.Craft.spring_boot.repositories;

import com.RDR2Hunt.Craft.spring_boot.models.ItemFavorito;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemFavoritoRepository extends JpaRepository<ItemFavorito, Long> {
    List<ItemFavorito> findByUsuarioId(Long usuarioId);
}
