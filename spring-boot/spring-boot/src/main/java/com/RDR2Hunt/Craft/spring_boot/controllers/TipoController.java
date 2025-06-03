package com.RDR2Hunt.Craft.spring_boot.controllers;

import com.RDR2Hunt.Craft.spring_boot.dto.TipoDTO;
import com.RDR2Hunt.Craft.spring_boot.models.Tipo;
import com.RDR2Hunt.Craft.spring_boot.services.TipoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tipos")
@CrossOrigin("*")
@RequiredArgsConstructor
public class TipoController {

    private final TipoService tipoService;

    @GetMapping("/all")
    public ResponseEntity<List<TipoDTO>> getAll() {
        return ResponseEntity.ok(tipoService.getAllTipos());
    }

    @GetMapping("/{nombre}")
    public ResponseEntity<Tipo> getByNombre(@PathVariable String nombre) {
        Optional<Tipo> tipo = tipoService.getByNombre(nombre);
        return tipo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    public ResponseEntity<Tipo> save(@RequestBody Tipo tipo) {
        return ResponseEntity.ok(tipoService.save(tipo));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        tipoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
