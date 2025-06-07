package com.RDR2Hunt.Craft.spring_boot.services;

import com.RDR2Hunt.Craft.spring_boot.dto.MaterialDTO;
import com.RDR2Hunt.Craft.spring_boot.models.Material;
import com.RDR2Hunt.Craft.spring_boot.repositories.MaterialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MaterialService {

    private MaterialRepository materialRepository;

    public List<MaterialDTO> getAllMaterials() {
        return materialRepository.findAll().stream().map(material -> {
            MaterialDTO dto = new MaterialDTO();
            dto.setId(material.getId());
            dto.setNombre(material.getNombre());
            return dto;
        }).collect(Collectors.toList());
    }

    public Optional<Material> getByNombre(String nombre) {
        return materialRepository.findByNombre(nombre);
    }

    public Material save(Material material) {
        return materialRepository.save(material);
    }

    public void delete(Long id) {
        materialRepository.deleteById(id);
    }
}
