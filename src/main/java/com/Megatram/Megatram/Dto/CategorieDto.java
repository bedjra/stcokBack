package com.Megatram.Megatram.Dto;

public class CategorieDto {

    private String nom;
    private int nProd;
    private Long id;


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getnProd() {
        return nProd;
    }

    public void setnProd(int nProd) {
        this.nProd = nProd;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
