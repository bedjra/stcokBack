package com.Megatram.Megatram.repository;

import com.Megatram.Megatram.Entity.Categorie;
import com.Megatram.Megatram.Entity.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduitRepos extends JpaRepository<Produit, Long> {
    Produit findByCodeBarre(String codeBarre);
    Long countByCategorie(Categorie categorie);

}