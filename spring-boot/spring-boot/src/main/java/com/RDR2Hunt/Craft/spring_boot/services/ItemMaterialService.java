package com.RDR2Hunt.Craft.spring_boot.services;

import com.RDR2Hunt.Craft.spring_boot.dto.ItemMaterialDTO;
import com.RDR2Hunt.Craft.spring_boot.models.ItemMaterial;
import com.RDR2Hunt.Craft.spring_boot.repositories.ItemMaterialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemMaterialService {

    private final ItemMaterialRepository itemMaterialRepository;

    public List<ItemMaterialDTO> getAllItemMaterials() {
        return itemMaterialRepository.findAll().stream().map(im -> {
            ItemMaterialDTO dto = new ItemMaterialDTO();
            dto.setId(im.getId());
            dto.setItemId(im.getItem().getId());
            dto.setItemNombre(im.getItem().getNombre());
            dto.setMaterialId(im.getMaterial().getId());
            dto.setMaterialNombre(im.getMaterial().getNombre());
            dto.setCantidad(im.getCantidad());
            return dto;
        }).collect(Collectors.toList());
    }

    public ItemMaterial save(ItemMaterial itemMaterial) {
        return itemMaterialRepository.save(itemMaterial);
    }

    public void delete(Long id) {
        itemMaterialRepository.deleteById(id);
    }
}
