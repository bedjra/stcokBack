package com.Megatram.Megatram.service;

import com.Megatram.Megatram.Entity.Facture;
import com.Megatram.Megatram.repository.FactureRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FactureService {

    private final FactureRepo factureRepository;

    public FactureService(FactureRepo factureRepository) {
        this.factureRepository = factureRepository;
    }

    public Facture saveFacture(Facture facture) {
        return factureRepository.save(facture);
    }

    public List<Facture> getAllFactures() {
        return factureRepository.findAll();
    }

    public Optional<Facture> getFactureById(String id) {
        return factureRepository.findById(Long.valueOf(id));
    }

    public Facture updateFacture(String id, Facture updatedFacture) {
        return factureRepository.findById(Long.valueOf(id)).map(f -> {
            f.setNom(updatedFacture.getNom());
            f.setLogoUrl(updatedFacture.getLogoUrl());
            f.setHeader(updatedFacture.getHeader());
            f.setFooter(updatedFacture.getFooter());
            f.setColor(updatedFacture.getColor());
            return factureRepository.save(f);
        }).orElse(null);
    }

    public void deleteFacture(String id) {
        factureRepository.deleteById(Long.valueOf(id));
    }
}
