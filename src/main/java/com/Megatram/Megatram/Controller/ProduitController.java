package com.Megatram.Megatram.Controller;

import com.Megatram.Megatram.Dto.ProduitDto;
import com.Megatram.Megatram.Entity.Produit;
import com.Megatram.Megatram.repository.ProduitRepos;
import com.Megatram.Megatram.service.ProduitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/produit")
@Tag(name = "Produit", description = "Gestion des Produits")

public class ProduitController {

    @Autowired
    private ProduitRepos produitRepository;

    @Autowired
    private ProduitService produitService;

    @Operation(summary = "add un produit")
    @PostMapping
    public ResponseEntity<String> ajouterProduit(@RequestBody Produit produit) throws Exception {
        // 1. Sauvegarder le produit (génère automatiquement le code-barres)
        Produit saved = produitRepository.save(produit);

        // 2. Nom du fichier d’image
        String filename = "barcode-" + saved.getId() + ".png";

        // 3. Générer l'image du code-barres
        produitService.generateBarcodeImage(saved.getCodeBarre(), filename);

        // 4. Retourner juste un message
        return ResponseEntity.status(201).body("Produit bien ajouté");
    }

    @Operation(summary = "Liste de tous les produits")
    @GetMapping
    public ResponseEntity<List<ProduitDto>> getAllProduits() {
        return ResponseEntity.ok(produitService.getAllProduits());
    }

    @Operation(summary = "Récupérer un produit par ID")
    @GetMapping("/{id}")
    public ResponseEntity<Produit> getProduitById(@PathVariable Long id) {
        return produitService.getProduitById(id);
    }

    @Operation(summary = "Mettre à jour un produit")
    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduit(@PathVariable Long id, @RequestBody Produit produit) {
        return produitService.updateProduit(id, produit);
    }

    @Operation(summary = "Supprimer un produit")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduit(@PathVariable Long id) {
        return produitService.deleteProduit(id);
    }

    @Operation(summary = "Get un barcode by id")
    @GetMapping("/code/{id}")
    public ResponseEntity<Resource> getBarcodeImageByProduitId(@PathVariable Long id) throws IOException {
        // Recherche du produit par son ID
        Optional<Produit> optionalProduit = produitRepository.findById(id);

        if (optionalProduit.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // Construction du chemin vers l’image générée (ex: barcodes/barcode-1.png)
        String filename = "barcode-" + id + ".png";
        Path imagePath = Paths.get("barcodes").resolve(filename);

        if (!Files.exists(imagePath)) {
            return ResponseEntity.notFound().build();
        }

        Resource fileResource = new UrlResource(imagePath.toUri());

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(fileResource);
    }


    @Operation(summary = "Importation des produits")
    @PostMapping("/import")
    public ResponseEntity<?> importerProduits(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty() || !file.getOriginalFilename().endsWith(".xlsx")) {
            return ResponseEntity.badRequest().body("Veuillez fournir un fichier Excel (.xlsx) valide.");
        }

        List<Produit> produits = produitService.importerProduitsDepuisExcel(file);
        return ResponseEntity.ok("Importation réussie de " + produits.size() + " produits.");
    }


    @GetMapping("/code/{codeBarre}")
    public ResponseEntity<?> getProduitByCode(@PathVariable String codeBarre) {
        Produit produit = produitRepository.findByCodeBarre(codeBarre);
        if (produit != null) {
            return ResponseEntity.ok(produit);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produit non trouvé");
        }
    }


}
