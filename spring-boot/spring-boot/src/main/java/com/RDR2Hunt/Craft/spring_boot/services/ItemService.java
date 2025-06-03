package com.RDR2Hunt.Craft.spring_boot.services;

import com.RDR2Hunt.Craft.spring_boot.models.Item;
import com.RDR2Hunt.Craft.spring_boot.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    public List<Item> getAll() {
        return itemRepository.findAll();
    }

    public Optional<Item> getByNombre(String nombre) {
        return itemRepository.findByNombre(nombre);
    }

    public Item guardar(Item item) {
        return itemRepository.save(item);
    }

    public void eliminar(Long id) {
        itemRepository.deleteById(id);
    }
}