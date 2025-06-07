package com.RDR2Hunt.Craft.spring_boot.services;

import com.RDR2Hunt.Craft.spring_boot.dto.TipoDTO;
import com.RDR2Hunt.Craft.spring_boot.models.Tipo;
import com.RDR2Hunt.Craft.spring_boot.repositories.TipoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TipoService {
    @Autowired
    private final TipoRepository tipoRepository;

    public List<TipoDTO> getAllTipos() {
        return tipoRepository.findAll().stream().map(tipo -> {
            TipoDTO dto = new TipoDTO();
            dto.setId(tipo.getId());
            dto.setNombre(tipo.getNombre());
            return dto;
        }).collect(Collectors.toList());
    }

    public Optional<Tipo> getByNombre(String nombre) {
        return tipoRepository.findByNombre(nombre);
    }

    public Tipo save(Tipo tipo) {
        return tipoRepository.save(tipo);
    }

    public void delete(Long id) {
        tipoRepository.deleteById(id);
    }
}
