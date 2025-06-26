package com.Megatram.Megatram.service;


import com.Megatram.Megatram.Entity.Categorie;
import com.Megatram.Megatram.repository.CategorieRep;
import com.Megatram.Megatram.repository.ProduitRepos;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategorieService {

    @Autowired
    private CategorieRep categorieRepository;

    @Autowired
    private ProduitRepos produitRepository;

    public List<Categorie> getAll() {
        return categorieRepository.findAll();
    }

    public List<Map<String, Object>> getAllWithProductCount() {
        List<Categorie> categories = categorieRepository.findAll();
        List<Map<String, Object>> result = new ArrayList<>();

        for (Categorie cat : categories) {
            Long count = produitRepository.countByCategorie(cat);
            Map<String, Object> map = new HashMap<>();
            map.put("id", cat.getId());
            map.put("nom", cat.getNom());
            map.put("nbProduits", count);
            result.add(map);
        }
        return result;
    }

    public Categorie getById(Long id) {
        return categorieRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categorie non trouvée avec ID: " + id));
    }

    public Categorie create(Categorie categorie) {
        return categorieRepository.save(categorie);
    }

    public Categorie update(Long id, Categorie data) {
        Categorie existing = getById(id);
        existing.setNom(data.getNom());
        return categorieRepository.save(existing);
    }

    public void delete(Long id) {
        if (!categorieRepository.existsById(id)) {
            throw new EntityNotFoundException("Categorie non trouvée avec ID: " + id);
        }
        categorieRepository.deleteById(id);
    }
}
