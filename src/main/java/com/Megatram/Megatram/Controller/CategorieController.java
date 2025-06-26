package com.Megatram.Megatram.Controller;

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
    public ResponseEntity<List<Map<String, Object>>> getAll() {
        return ResponseEntity.ok(categorieService.getAllWithProductCount());
    }

    @Operation(summary = "get by id ")
    @GetMapping("/{id}")
    public Categorie getById(@PathVariable Long id) {
        return categorieService.getById(id);
    }

    @Operation(summary = "add ")
    @PostMapping
    public Categorie create(@RequestBody Categorie categorie) {
        return categorieService.create(categorie);
    }

    @Operation(summary = "put ")
    @PutMapping("/{id}")
    public Categorie update(@PathVariable Long id, @RequestBody Categorie categorie) {
        return categorieService.update(id, categorie);
    }

    @Operation(summary = "delete by id ")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        categorieService.delete(id);
        return ResponseEntity.ok().build();
    }
}
