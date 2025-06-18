package com.RDR2Hunt.Craft.spring_boot.repositories;


import com.RDR2Hunt.Craft.spring_boot.models.OutfitFavorito;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OutfitFavoritoRepository extends JpaRepository<OutfitFavorito, Long> {
    List<OutfitFavorito> findByUsuarioId(Long usuarioId);
}
