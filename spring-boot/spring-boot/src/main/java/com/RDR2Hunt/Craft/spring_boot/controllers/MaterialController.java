package com.RDR2Hunt.Craft.spring_boot.controllers;

import com.RDR2Hunt.Craft.spring_boot.dto.MaterialDTO;
import com.RDR2Hunt.Craft.spring_boot.models.Material;
import com.RDR2Hunt.Craft.spring_boot.services.MaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/materials")
@CrossOrigin("*")
@RequiredArgsConstructor
public class MaterialController {

    private final MaterialService materialService;

    @GetMapping("/all")
    public ResponseEntity<List<MaterialDTO>> getAll() {
        return ResponseEntity.ok(materialService.getAllMaterials());
    }

    @GetMapping("/{nombre}")
    public ResponseEntity<Material> getByNombre(@PathVariable String nombre) {
        Optional<Material> material = materialService.getByNombre(nombre);
        return material.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    public ResponseEntity<Material> save(@RequestBody Material material) {
        return ResponseEntity.ok(materialService.save(material));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        materialService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
