package com.RDR2Hunt.Craft.spring_boot.controllers;

import com.RDR2Hunt.Craft.spring_boot.models.Item;
import com.RDR2Hunt.Craft.spring_boot.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/items")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @GetMapping("/getAll")
    public List<Item> getAll() {
        return itemService.getAll();
    }

    @GetMapping("/getByNombre")
    public Optional<Item> getByNombre(@RequestParam String nombre) {
        return itemService.getByNombre(nombre);
    }

    @PostMapping
    public Item guardar(@RequestBody Item item) {
        return itemService.guardar(item);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        itemService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}