package com.RDR2Hunt.Craft.spring_boot.controllers;

import com.RDR2Hunt.Craft.spring_boot.models.MaterialConseguido;
import com.RDR2Hunt.Craft.spring_boot.services.MaterialConseguidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/material-conseguido")
@RequiredArgsConstructor
@CrossOrigin("*")
public class MaterialConseguidoController {
    private  MaterialConseguidoService service;

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<MaterialConseguido>> getByUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(service.getByUsuario(usuarioId));
    }

    @PostMapping("/save")
    public ResponseEntity<MaterialConseguido> save(@RequestBody MaterialConseguido favorito) {
        return ResponseEntity.ok(service.save(favorito));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
