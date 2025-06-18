package com.RDR2Hunt.Craft.spring_boot.controllers;


import com.RDR2Hunt.Craft.spring_boot.models.OutfitFavorito;
import com.RDR2Hunt.Craft.spring_boot.services.OutfitFavoritoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/outfit-favoritos")
@RequiredArgsConstructor
@CrossOrigin("*")
public class OutfitFavoritoController {
    private OutfitFavoritoService service;

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<OutfitFavorito>> getByUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(service.getByUsuario(usuarioId));
    }

    @PostMapping("/save")
    public ResponseEntity<OutfitFavorito> save(@RequestBody OutfitFavorito favorito) {
        return ResponseEntity.ok(service.save(favorito));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
