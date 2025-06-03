package com.RDR2Hunt.Craft.spring_boot.dto;

public class ItemDTO {
    private Long id;
    private String nombre;
    private Double precio;
    private String tipoNombre;
    private String outfitNombre;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOutfitNombre() {
        return outfitNombre;
    }

    public void setOutfitNombre(String outfitNombre) {
        this.outfitNombre = outfitNombre;
    }

    public String getTipoNombre() {
        return tipoNombre;
    }

    public void setTipoNombre(String tipoNombre) {
        this.tipoNombre = tipoNombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
