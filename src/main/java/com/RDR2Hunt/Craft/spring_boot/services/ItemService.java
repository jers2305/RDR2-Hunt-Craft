package com.RDR2Hunt.Craft.spring_boot.services;

import com.RDR2Hunt.Craft.spring_boot.dto.ItemDTO;
import com.RDR2Hunt.Craft.spring_boot.dto.ItemMaterialSimpleDTO;
import com.RDR2Hunt.Craft.spring_boot.models.Item;
import com.RDR2Hunt.Craft.spring_boot.models.ItemMaterial;
import com.RDR2Hunt.Craft.spring_boot.repositories.ItemMaterialRepository;
import com.RDR2Hunt.Craft.spring_boot.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ItemMaterialRepository itemMaterialRepository;

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Optional<Item> getItemByNombre(String nombre) {
        Optional<Item> itemOpt = itemRepository.findByNombre(nombre);
        itemOpt.ifPresent(item -> {
            if (item.getTipo() != null) {
                item.getTipo().getId();  // fuerza carga del proxy
            }
        });
        return itemOpt;
    }
    public Optional<Item> getItemById(Long id) {
        Optional<Item> itemOpt = itemRepository.findById(id);
        itemOpt.ifPresent(item -> {
            if (item.getTipo() != null) {
                item.getTipo().getNombre(); // Fuerza carga
            }
            if (item.getOutfit() != null) {
                item.getOutfit().getNombre(); // Fuerza carga
            }
        });
        return itemOpt;
    }

    public Optional<ItemDTO> getItemDetailById(Long id) {
        Optional<Item> itemOpt = itemRepository.findById(id);

        if (itemOpt.isEmpty()) {
            return Optional.empty();
        }

        Item item = itemOpt.get();

        // Forzar carga de relaciones si usas lazy
        if (item.getTipo() != null) {
            item.getTipo().getNombre();
        }
        if (item.getOutfit() != null) {
            item.getOutfit().getNombre();
        }

        ItemDTO dto = new ItemDTO();
        dto.setId(item.getId());
        dto.setNombre(item.getNombre());
        dto.setPrecio(item.getPrecio());
        dto.setImage(item.getImage());

        if (item.getTipo() != null) {
            dto.setTipoId(item.getTipo().getId());
            dto.setTipoNombre(item.getTipo().getNombre());
        }

        if (item.getOutfit() != null) {
            dto.setOutfitId(item.getOutfit().getId());
            dto.setOutfitNombre(item.getOutfit().getNombre());
        }

        // Obtener materiales relacionados
        List<ItemMaterial> itemMaterials = itemMaterialRepository.findByItem_Id(item.getId());

        List<ItemMaterialSimpleDTO> materiales = itemMaterials.stream()
                .map(im -> new ItemMaterialSimpleDTO(
                        im.getMaterial().getId(),
                        im.getMaterial().getNombre(),
                        im.getCantidad()
                ))
                .collect(Collectors.toList());

        dto.setMateriales(materiales);

        return Optional.of(dto);
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
    public List<Item> findByOutfitId(Long outfitId) {
        return itemRepository.findByOutfitId(outfitId);
    }

    public List<ItemDTO> findByTipoId(Long tipoId) {
        List<Item> items = itemRepository.findByTipoId(tipoId);
        return items.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<ItemDTO> findByMaterialId(Long materialId) {
        List<Item> items = itemRepository.findByMateriales_Id(materialId);
        return items.stream().map(this::convertToDTO).collect(Collectors.toList());
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
    public List<ItemDTO> getItemsByOutfitId(Long outfitId) {
        List<Item> items = itemRepository.findByOutfitId(outfitId);
        return items.stream().map(item -> {
            ItemDTO dto = new ItemDTO();
            dto.setId(item.getId());
            dto.setNombre(item.getNombre());
            dto.setPrecio(item.getPrecio());
            dto.setTipoNombre(item.getTipo() != null ? item.getTipo().getNombre() : null);
            dto.setOutfitNombre(item.getOutfit() != null ? item.getOutfit().getNombre() : null);
            return dto;
        }).collect(Collectors.toList());
    }
    private ItemDTO convertToDTO(Item item) {
        ItemDTO dto = new ItemDTO();
        dto.setId(item.getId());
        dto.setNombre(item.getNombre());
        dto.setPrecio(item.getPrecio());
        dto.setTipoNombre(item.getTipo() != null ? item.getTipo().getNombre() : null);
        dto.setOutfitNombre(item.getOutfit() != null ? item.getOutfit().getNombre() : null);
        return dto;
    }

}