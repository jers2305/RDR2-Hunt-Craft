package com.RDR2Hunt.Craft.spring_boot.controllers;

import com.RDR2Hunt.Craft.spring_boot.models.ItemCrafteado;
import com.RDR2Hunt.Craft.spring_boot.services.ItemCrafteadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item-crafteados")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ItemCrafteadoController {
    private  ItemCrafteadoService service;

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<ItemCrafteado>> getByUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(service.getByUsuario(usuarioId));
    }

    @PostMapping("/save")
    public ResponseEntity<ItemCrafteado> save(@RequestBody ItemCrafteado favorito) {
        return ResponseEntity.ok(service.save(favorito));
    }

    @DeleteMapping("/delete/{id}")

    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
