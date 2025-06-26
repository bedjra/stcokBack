//package com.Megatram.Megatram.Entity;
//
//import jakarta.persistence.*;
//import java.util.Date;
//
//@Entity
//public class Stock {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "produit_id", nullable = false)
//    private Produit produit;
//
//    @ManyToOne
//    @JoinColumn(name = "entrepot_id", nullable = false)
//    private Entrepot entrepot;
//
//    private int quantite;
//
//
//
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date date;
//
//    @PrePersist
//    protected void onCreate() {
//        if (date == null) {
//            date = new Date();
//        }
//    }
//
//    // ======= Getters et Setters =======
//
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
//    public Entrepot getEntrepot() {
//        return entrepot;
//    }
//
//    public void setEntrepot(Entrepot entrepot) {
//        this.entrepot = entrepot;
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
//    public Date getDate() {
//        return date;
//    }
//
//    public void setDate(Date date) {
//        this.date = date;
//    }
//}
