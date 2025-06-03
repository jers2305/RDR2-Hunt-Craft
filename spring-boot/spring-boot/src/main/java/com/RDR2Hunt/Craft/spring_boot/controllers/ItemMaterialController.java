package com.RDR2Hunt.Craft.spring_boot.controllers;

import com.RDR2Hunt.Craft.spring_boot.models.ItemMaterial;
import com.RDR2Hunt.Craft.spring_boot.dto.ItemMaterialDTO;
import com.RDR2Hunt.Craft.spring_boot.services.ItemMaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item-materials")
@CrossOrigin("*")
@RequiredArgsConstructor
public class ItemMaterialController {

    private final ItemMaterialService itemMaterialService;

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
}
