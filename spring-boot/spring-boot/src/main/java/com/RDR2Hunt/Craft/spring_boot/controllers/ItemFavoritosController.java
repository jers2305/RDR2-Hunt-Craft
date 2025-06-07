package com.RDR2Hunt.Craft.spring_boot.controllers;

import com.RDR2Hunt.Craft.spring_boot.models.ItemFavorito;
import com.RDR2Hunt.Craft.spring_boot.services.ItemFavoritoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item-favoritos")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ItemFavoritosController {
    private final ItemFavoritoService service;

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<ItemFavorito>> getByUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(service.getByUsuario(usuarioId));
    }

    @PostMapping("/save")
    public ResponseEntity<ItemFavorito> save(@RequestBody ItemFavorito favorito) {
        return ResponseEntity.ok(service.save(favorito));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
