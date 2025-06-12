package com.RDR2Hunt.Craft.spring_boot.controllers;

import com.RDR2Hunt.Craft.spring_boot.dto.ItemDTO;
import com.RDR2Hunt.Craft.spring_boot.models.Item;
import com.RDR2Hunt.Craft.spring_boot.repositories.ItemRepository;
import com.RDR2Hunt.Craft.spring_boot.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/items")
@CrossOrigin("*")
public class ItemController {
    @Autowired
    private ItemService itemService;
    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/all")
    public ResponseEntity<List<ItemDTO>> getAllItems() {
        List<ItemDTO> items = itemService.getAllItemDTOs();
        return ResponseEntity.ok(items);
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<Item> getItemByNombre(@PathVariable String nombre) {
        Optional<Item> itemOptional = itemService.getItemByNombre(nombre);
        return itemOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    public ResponseEntity<Item> saveItem(@RequestBody Item item) {
        Item savedItem = itemService.saveItem(item);
        return ResponseEntity.ok(savedItem);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        if (!itemService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        itemService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/by-outfit/{outfitId}")
    public ResponseEntity<List<ItemDTO>> getItemsByOutfit(@PathVariable Long outfitId) {
        List<ItemDTO> items = itemService.getItemsByOutfitId(outfitId);
        return ResponseEntity.ok(items);
    }


    @GetMapping("/by-tipo/{tipoId}")
    public ResponseEntity<List<ItemDTO>> getItemsByTipo(@PathVariable Long tipoId) {
        List<ItemDTO> items = itemService.findByTipoId(tipoId);
        return ResponseEntity.ok(items);
    }

    @GetMapping("/by-material/{materialId}")
    public ResponseEntity<List<ItemDTO>> getItemsByMaterial(@PathVariable Long materialId) {
        List<ItemDTO> items = itemService.findByMaterialId(materialId);
        return ResponseEntity.ok(items);
    }


}