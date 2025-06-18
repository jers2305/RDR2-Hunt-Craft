package com.RDR2Hunt.Craft.spring_boot.dto;

import java.util.List;

public class ItemDTO {
    private Long id;
    private String nombre;
    private Double precio;
    private String image;
    private Long tipoId;
    private String tipoNombre;
    private Long outfitId;
    private String outfitNombre;
    private List<ItemMaterialSimpleDTO> materiales;

    public ItemDTO() {}

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

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

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Long getTipoId() {
        return tipoId;
    }

    public void setTipoId(Long tipoId) {
        this.tipoId = tipoId;
    }

    public String getTipoNombre() {
        return tipoNombre;
    }

    public void setTipoNombre(String tipoNombre) {
        this.tipoNombre = tipoNombre;
    }

    public Long getOutfitId() {
        return outfitId;
    }

    public void setOutfitId(Long outfitId) {
        this.outfitId = outfitId;
    }

    public String getOutfitNombre() {
        return outfitNombre;
    }

    public void setOutfitNombre(String outfitNombre) {
        this.outfitNombre = outfitNombre;
    }

    public List<ItemMaterialSimpleDTO> getMateriales() {
        return materiales;
    }

    public void setMateriales(List<ItemMaterialSimpleDTO> materiales) {
        this.materiales = materiales;
    }
}
