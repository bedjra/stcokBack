//package com.Megatram.Megatram.Entity;
//
//import jakarta.persistence.*;
//
//import java.util.List;
//
//@Entity
//public class Items {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private int quantite;
//    private double prixTotal;
//    private int pourcentage;
//
//    @ManyToOne
//    @JoinColumn(name = "produit_id")
//    private Produit produit;
//
//    @ManyToOne
//    @JoinColumn(name = "stock_id")
//    private Stock stock;
//
//
//    // Getters & Setters
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Produit getProduit() {
//        return produit;
//    }
//
//    public void setProduit(Produit produit) {
//        this.produit = produit;
//    }
//
//    public int getQuantite() {
//        return quantite;
//    }
//
//    public void setQuantite(int quantite) {
//        this.quantite = quantite;
//    }
//
//    public double getPrixTotal() {
//        return prixTotal;
//    }
//
//    public void setPrixTotal(double prixTotal) {
//        this.prixTotal = prixTotal;
//    }
//
//    public int getPourcentage() {
//        return pourcentage;
//    }
//
//    public void setPourcentage(int pourcentage) {
//        this.pourcentage = pourcentage;
//    }
//
//    public Stock getStock() {
//        return stock;
//    }
//
//    public void setStock(Stock stock) {
//        this.stock = stock;
//    }
//}
