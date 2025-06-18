package com.RDR2Hunt.Craft.spring_boot.dto;

public class ItemMaterialSimpleDTO {
    private Long materialId;
    private String materialNombre;
    private int cantidad;

    public ItemMaterialSimpleDTO(Long materialId, String materialNombre, int cantidad) {
        this.materialId = materialId;
        this.materialNombre = materialNombre;
        this.cantidad = cantidad;
    }

    // Getters y setters
    public Long getMaterialId() {
        return materialId;
    }
    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }
    public String getMaterialNombre() {
        return materialNombre;
    }
    public void setMaterialNombre(String materialNombre) {
        this.materialNombre = materialNombre;
    }
    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}

