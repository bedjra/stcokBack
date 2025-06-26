//package com.Megatram.Megatram.Entity;
//
//import jakarta.persistence.*;
//
//import java.util.Date;
//
//@Entity
//public class Vente {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String client;
//
//    private double total;
//
//    private double paye;
//
//    private double reste;
//
//    private Date date;
//
//    @ManyToOne
//    @JoinColumn(name = "facture_id")
//    private Facture facture;
//
//    // Getters & Setters
//
//
//}
