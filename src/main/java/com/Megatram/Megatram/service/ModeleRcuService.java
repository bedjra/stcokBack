package com.Megatram.Megatram.service;

import com.Megatram.Megatram.Entity.ModeleRecu;
import com.Megatram.Megatram.repository.FactureRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModeleRcuService {

    private final FactureRepo factureRepository;

    public ModeleRcuService(FactureRepo factureRepository) {
        this.factureRepository = factureRepository;
    }

    public ModeleRecu saveFacture(ModeleRecu modeleRecu) {
        return factureRepository.save(modeleRecu);
    }

    public List<ModeleRecu> getAllFactures() {
        return factureRepository.findAll();
    }

    public Optional<ModeleRecu> getFactureById(String id) {
        return factureRepository.findById(Long.valueOf(id));
    }

    public ModeleRecu updateFacture(String id, ModeleRecu updatedModeleRecu) {
        return factureRepository.findById(Long.valueOf(id)).map(f -> {
            f.setNom(updatedModeleRecu.getNom());
            f.setLogoUrl(updatedModeleRecu.getLogoUrl());
            f.setHeader(updatedModeleRecu.getHeader());
            f.setFooter(updatedModeleRecu.getFooter());
            f.setColor(updatedModeleRecu.getColor());
            return factureRepository.save(f);
        }).orElse(null);
    }

    public void deleteFacture(String id) {
        factureRepository.deleteById(Long.valueOf(id));
    }
}
