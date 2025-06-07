package com.RDR2Hunt.Craft.spring_boot.controllers;

import com.RDR2Hunt.Craft.spring_boot.dto.OutfitDTO;
import com.RDR2Hunt.Craft.spring_boot.models.Outfit;
import com.RDR2Hunt.Craft.spring_boot.services.OutfitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/outfits")
@CrossOrigin("*")
@RequiredArgsConstructor
public class OutfitController {

    private OutfitService outfitService;

    @GetMapping("/all")
    public ResponseEntity<List<OutfitDTO>> getAll() {
        return ResponseEntity.ok(outfitService.getAllOutfits());
    }

    @GetMapping("/{nombre}")
    public ResponseEntity<Outfit> getByNombre(@PathVariable String nombre) {
        Optional<Outfit> outfit = outfitService.getByNombre(nombre);
        return outfit.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    public ResponseEntity<Outfit> save(@RequestBody Outfit outfit) {
        return ResponseEntity.ok(outfitService.save(outfit));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        outfitService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
