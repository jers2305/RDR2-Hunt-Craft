package com.RDR2Hunt.Craft.spring_boot.repositories;

import com.RDR2Hunt.Craft.spring_boot.models.Item;
import com.RDR2Hunt.Craft.spring_boot.models.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    Optional<Item> findByNombre(String nombre);
}
