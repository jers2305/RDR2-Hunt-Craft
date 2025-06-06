package com.RDR2Hunt.Craft.spring_boot.repositories;

import com.RDR2Hunt.Craft.spring_boot.models.ItemCrafteado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemCrafteadoRepository extends JpaRepository<ItemCrafteado, Long> {
    List<ItemCrafteado> findByUsuarioId(Long usuarioId);
}
