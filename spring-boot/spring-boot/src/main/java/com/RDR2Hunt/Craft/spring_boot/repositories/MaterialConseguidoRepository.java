package com.RDR2Hunt.Craft.spring_boot.repositories;


import com.RDR2Hunt.Craft.spring_boot.models.MaterialConseguido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaterialConseguidoRepository extends JpaRepository<MaterialConseguido, Long> {
    List<MaterialConseguido> findByUsuarioId(Long usuarioId);
}
