package com.RDR2Hunt.Craft.spring_boot.repositories;

import com.RDR2Hunt.Craft.spring_boot.models.Outfit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OutfitRepository extends JpaRepository<Outfit, Long> {
    Optional<Outfit> findByNombre(String nombre);
}
