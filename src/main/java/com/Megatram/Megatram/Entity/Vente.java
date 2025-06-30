package com.Megatram.Megatram.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Vente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ref;

    private String caissier;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    private double paiement; // calculé automatiquement

    @OneToMany(mappedBy = "vente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LigneVente> lignes;

    @PrePersist
    @PreUpdate
    public void calculerPaiementTotal() {
        if (lignes != null && !lignes.isEmpty()) {
            this.paiement = lignes.stream()
                    .mapToDouble(LigneVente::getTotal)
                    .sum();
        } else {
            this.paiement = 0.0;
        }
    }



    // Getters & Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getCaissier() {
        return caissier;
    }

    public void setCaissier(String caissier) {
        this.caissier = caissier;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public double getPaiement() {
        return paiement;
    }

    public void setPaiement(double paiement) {
        this.paiement = paiement;
    }

    public List<LigneVente> getLignes() {
        return lignes;
    }

    public void setLignes(List<LigneVente> lignes) {
        this.lignes = lignes;
    }
}
