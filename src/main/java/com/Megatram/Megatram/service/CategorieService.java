package com.Megatram.Megatram.service;


import com.Megatram.Megatram.Dto.CategorieDto;
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
import java.util.stream.Collectors;

@Service
public class CategorieService {

    @Autowired
    private CategorieRep categorieRepository;

    @Autowired
    private ProduitRepos produitRepository;

    public long getNombreTotalCategories() {
        return categorieRepository.count();
    }

    public List<CategorieDto> getAllCategories() {
        return categorieRepository.findAll()
                .stream()
                .map(c -> {
                    CategorieDto dto = new CategorieDto();
                    dto.setId(c.getId());
                    dto.setNom(c.getNom());
                    dto.setnProd(c.getProduits() != null ? c.getProduits().size() : 0);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public Categorie addCategorie(CategorieDto dto) {
        Categorie categorie = new Categorie();
        categorie.setNom(dto.getNom());
        return categorieRepository.save(categorie);
    }

    public void deleteCategorie(Long id) {
        categorieRepository.deleteById(id);
    }
    public void deleteCategoriesByIds(List<Long> ids) {
        categorieRepository.deleteAllById(ids);
    }

    public void deleteAllCategories() {
        categorieRepository.deleteAll();
    }



    public Categorie getById(Long id) {
        return categorieRepository.findById(id)
                .orElse(null); // Ou tu peux lancer une exception si tu veux
    }

    public Categorie updateCategorie(Long id, CategorieDto dto) {
        return categorieRepository.findById(id).map(categorie -> {
            categorie.setNom(dto.getNom());
            return categorieRepository.save(categorie);
        }).orElseThrow(() -> new RuntimeException("Catégorie non trouvée"));
    }
}
