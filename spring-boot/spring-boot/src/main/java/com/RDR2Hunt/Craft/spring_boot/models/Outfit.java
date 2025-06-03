package com.RDR2Hunt.Craft.spring_boot.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import com.RDR2Hunt.Craft.spring_boot.models.Item;

@Entity
public class Outfit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @OneToMany(mappedBy = "outfit", cascade = CascadeType.ALL)
    private List<Item> items = new ArrayList<>();



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
