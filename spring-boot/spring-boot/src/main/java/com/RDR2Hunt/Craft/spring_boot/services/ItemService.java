package com.RDR2Hunt.Craft.spring_boot.services;

import com.RDR2Hunt.Craft.spring_boot.dto.ItemDTO;
import com.RDR2Hunt.Craft.spring_boot.models.Item;
import com.RDR2Hunt.Craft.spring_boot.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;


    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Optional<Item> getItemByNombre(String nombre) {
        return itemRepository.findByNombre(nombre);
    }

    public Item saveItem(Item item) {
        return itemRepository.save(item);
    }

    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }
    public boolean existsById(Long id) {
        return itemRepository.existsById(id);
    }
    public List<ItemDTO> getAllItemDTOs() {
        return itemRepository.findAll().stream().map(item -> {
            ItemDTO dto = new ItemDTO();
            dto.setId(item.getId());
            dto.setNombre(item.getNombre());
            dto.setPrecio(item.getPrecio());
            dto.setTipoNombre(item.getTipo() != null ? item.getTipo().getNombre() : null);
            dto.setOutfitNombre(item.getOutfit() != null ? item.getOutfit().getNombre() : null);
            return dto;
        }).collect(Collectors.toList());
    }
}