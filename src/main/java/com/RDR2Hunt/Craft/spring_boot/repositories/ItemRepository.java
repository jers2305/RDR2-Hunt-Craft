package com.RDR2Hunt.Craft.spring_boot.repositories;

import com.RDR2Hunt.Craft.spring_boot.models.Item;
import com.RDR2Hunt.Craft.spring_boot.models.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    Optional<Item> findByNombre(String nombre);
    List<Item> findByOutfitId(Long outfitId);
    List<Item> findByTipoId(Long tipoId);
    @Query("SELECT im.item FROM ItemMaterial im WHERE im.material.id = :materialId")
    List<Item> findItemsByMaterialId(@Param("materialId") Long materialId);
    List<Item> findByMateriales_Id(Long materialId); // si es relación @ManyToMany
}
