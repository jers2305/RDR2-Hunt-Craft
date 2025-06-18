package com.RDR2Hunt.Craft.spring_boot.dto;

import java.util.List;

public class OutfitDTO {
    private Long id;
    private String nombre;
    private String image;
    private List<ItemSimplifiedDTO> items;


    // Getters y setters
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<ItemSimplifiedDTO> getItems() {
        return items;
    }

    public void setItems(List<ItemSimplifiedDTO> items) {
        this.items = items;
    }
}
