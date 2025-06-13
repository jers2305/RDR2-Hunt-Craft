package com.RDR2Hunt.Craft.spring_boot.controllers;

import com.RDR2Hunt.Craft.spring_boot.models.ItemMaterial;
import com.RDR2Hunt.Craft.spring_boot.dto.ItemMaterialDTO;
import com.RDR2Hunt.Craft.spring_boot.services.ItemMaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item-materials")
@CrossOrigin("*")
@RequiredArgsConstructor
public class ItemMaterialController {
    @Autowired
    private ItemMaterialService itemMaterialService;

    @GetMapping("/all")
    public ResponseEntity<List<ItemMaterialDTO>> getAll() {
        return ResponseEntity.ok(itemMaterialService.getAllItemMaterials());
    }

    @PostMapping("/save")
    public ResponseEntity<ItemMaterial> save(@RequestBody ItemMaterial itemMaterial) {
        ItemMaterial saved = itemMaterialService.save(itemMaterial);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        itemMaterialService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/by-item/{itemId}")
    public ResponseEntity<List<ItemMaterialDTO>> getByItemId(@PathVariable Long itemId) {
        List<ItemMaterialDTO> items = itemMaterialService.findByItemId(itemId);
        return ResponseEntity.ok(items);
    }

    @GetMapping("/by-material/{materialId}")
    public ResponseEntity<List<ItemMaterialDTO>> getByMaterialId(@PathVariable Long materialId) {
        List<ItemMaterialDTO> materials = itemMaterialService.findByMaterialId(materialId);
        return ResponseEntity.ok(materials);
    }
}
