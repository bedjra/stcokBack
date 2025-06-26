package com.Megatram.Megatram.Controller;

import com.Megatram.Megatram.Dto.CategorieDto;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;


import com.Megatram.Megatram.Entity.Categorie;
import com.Megatram.Megatram.service.CategorieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("categorie")
@Tag(name = "Categorie", description = "Gestion des Categories")

public class CategorieController {

    @Autowired
    private CategorieService categorieService;

    @Operation(summary = "all ")
    @GetMapping
    public List<CategorieDto> getAllCategories() {
        return categorieService.getAllCategories();
    }


    @Operation(summary = "Récupérer une catégorie par son ID")
    @GetMapping("/{id}")
    public ResponseEntity<Categorie> getCategorieById(@PathVariable Long id) {
        Categorie categorie = categorieService.getById(id);
        if (categorie != null) {
            return ResponseEntity.ok(categorie);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "add ")
    @PostMapping
    public Categorie addCategorie(@RequestBody CategorieDto dto) {
        return categorieService.addCategorie(dto);
    }

    @Operation(summary = "put ")
    @PutMapping("/{id}")
    public Categorie updateCategorie(@PathVariable Long id, @RequestBody CategorieDto dto) {
        return categorieService.updateCategorie(id, dto);
    }

    @Operation(summary = "delete by id ")
    @DeleteMapping("/{id}")
    public void deleteCategorie(@PathVariable Long id) {
        categorieService.deleteCategorie(id);
    }


    @Operation(summary = "Supprimer plusieurs catégories ou toutes")
    @DeleteMapping
    public ResponseEntity<Void> deleteByIdsOrAll(
            @RequestBody List<Long> ids
    ) {
        if (ids == null) {
            return ResponseEntity.badRequest().build();
        }
        if (ids.isEmpty()) {
            categorieService.deleteAllCategories();
        } else {
            categorieService.deleteCategoriesByIds(ids);
        }
        return ResponseEntity.noContent().build();
    }




}
