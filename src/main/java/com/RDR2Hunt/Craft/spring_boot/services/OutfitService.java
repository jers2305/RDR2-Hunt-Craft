package com.RDR2Hunt.Craft.spring_boot.services;

import com.RDR2Hunt.Craft.spring_boot.dto.ItemSimplifiedDTO;
import com.RDR2Hunt.Craft.spring_boot.dto.OutfitDTO;
import com.RDR2Hunt.Craft.spring_boot.models.Outfit;
import com.RDR2Hunt.Craft.spring_boot.repositories.OutfitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OutfitService {

    @Autowired
    private OutfitRepository outfitRepository;

    public List<OutfitDTO> getAllOutfits() {
        return outfitRepository.findAll().stream().map(outfit -> {
            OutfitDTO dto = new OutfitDTO();
            dto.setId(outfit.getId());
            dto.setNombre(outfit.getNombre());
            dto.setImage(outfit.getImage());
            dto.setItems(outfit.getItems().stream().map(item -> {
                ItemSimplifiedDTO itemDTO = new ItemSimplifiedDTO();
                itemDTO.setId(item.getId());
                itemDTO.setNombre(item.getNombre());
                itemDTO.setPrecio(item.getPrecio());
                return itemDTO;
            }).collect(Collectors.toList()));
            return dto;
        }).collect(Collectors.toList());
    }
    public Optional<OutfitDTO> getOutfitById(Long id){
        Optional<Outfit> outfitOpt = outfitRepository.findById(id);

        if (outfitOpt.isEmpty()){
            return Optional.empty();
        }

        Outfit outfit = outfitOpt.get();

        OutfitDTO dto = new OutfitDTO();
        dto.setId(outfit.getId());
        dto.setNombre(outfit.getNombre());
        dto.setImage(outfit.getImage());
        return Optional.of(dto);
    }

    public Optional<Outfit> getByNombre(String nombre) {
        return outfitRepository.findByNombre(nombre);
    }

    public Outfit save(Outfit outfit) {
        return outfitRepository.save(outfit);
    }

    public void delete(Long id) {
        outfitRepository.deleteById(id);
    }
}
