package com.RDR2Hunt.Craft.spring_boot.services;

import com.RDR2Hunt.Craft.spring_boot.dto.OutfitDTO;
import com.RDR2Hunt.Craft.spring_boot.models.Outfit;
import com.RDR2Hunt.Craft.spring_boot.repositories.OutfitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OutfitService {

    private final OutfitRepository outfitRepository;

    public List<OutfitDTO> getAllOutfits() {
        return outfitRepository.findAll().stream().map(outfit -> {
            OutfitDTO dto = new OutfitDTO();
            dto.setId(outfit.getId());
            dto.setNombre(outfit.getNombre());
            return dto;
        }).collect(Collectors.toList());
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
