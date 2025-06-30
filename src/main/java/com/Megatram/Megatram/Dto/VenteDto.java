package com.Megatram.Megatram.Dto;

import java.util.List;

public class VenteDto {
    private Long id;
    private String ref;
    private String caissier;
    private String clientNom;
    private long clientId;
    private double paiement;
    private List<LigneVenteDto> lignes;

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

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

    public String getClientNom() {
        return clientNom;
    }

    public void setClientNom(String clientNom) {
        this.clientNom = clientNom;
    }

    public double getPaiement() {
        return paiement;
    }

    public void setPaiement(double paiement) {
        this.paiement = paiement;
    }

    public List<LigneVenteDto> getLignes() {
        return lignes;
    }

    public void setLignes(List<LigneVenteDto> lignes) {
        this.lignes = lignes;
    }
}
