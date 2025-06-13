package com.RDR2Hunt.Craft.spring_boot.repositories;

import com.RDR2Hunt.Craft.spring_boot.models.ItemMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemMaterialRepository extends JpaRepository<ItemMaterial, Long> {
    List<ItemMaterial> findByItem_Id(Long itemId);
    List<ItemMaterial> findByMaterial_Id(Long materialId);
   /* @Query("SELECT im FROM ItemMaterial im WHERE im.item.id = :itemId")
    List<ItemMaterial> findByItemId(@Param("itemId") Long itemId);

    @Query("SELECT im FROM ItemMaterial im WHERE im.material.id = :materialId")
    List<ItemMaterial> findByMaterialId(@Param("materialId") Long materialId);*/

}
