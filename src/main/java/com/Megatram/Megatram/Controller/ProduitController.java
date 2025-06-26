package com.Megatram.Megatram.Controller;

import com.Megatram.Megatram.Dto.AssignationProduitsDTO;
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

    @Operation(summary = "total")
    @GetMapping("/total")
    public ResponseEntity<Long> getNombreTotalProduits() {
        long total = produitService.getNombreTotalProduits();
        return ResponseEntity.ok(total);
    }

    @Operation(summary = "add un produit")
    @PostMapping
    public ResponseEntity<ProduitDto> createProduit(@RequestBody ProduitDto produitDto) {
        ProduitDto savedProduit = produitService.createProduit(produitDto);
        return ResponseEntity.ok(savedProduit);
    }

    @Operation(summary = "Importation des produits")
    @PostMapping(path = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> importerProduits(
            @RequestParam("file") MultipartFile file
    ) {
        if (file.isEmpty() || !file.getOriginalFilename().toLowerCase().endsWith(".xlsx")) {
            return ResponseEntity.badRequest()
                    .body("Veuillez fournir un fichier Excel (.xlsx) valide.");
        }
        List<Produit> produits = produitService.importerProduitsDepuisExcel(file);
        return ResponseEntity.ok("Importation réussie de " + produits.size() + " produits.");
    }


    @Operation(summary = "all ")
    @GetMapping
    public ResponseEntity<List<ProduitDto>> getAll() {
        return ResponseEntity.ok(produitService.getAllProduits());
    }

    @Operation(summary = "Récupérer un produit par son ID")
    @GetMapping("/{id}")
    public ResponseEntity<ProduitDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(produitService.getProduitById(id));
    }

    @Operation(summary = "put ")
    @PutMapping("/{id}")
    public ResponseEntity<ProduitDto> update(
            @PathVariable Long id,
            @RequestBody ProduitDto dto
    ) {
        return ResponseEntity.ok(produitService.updateProduit(id, dto));
    }

    @PutMapping("/assignation")
    public ResponseEntity<String> assignerCategorieEtEntrepot(@RequestBody AssignationProduitsDTO dto) {
        produitService.assignerCategorieEtEntrepot(dto);
        return ResponseEntity.ok("Assignation effectuée avec succès.");
    }

    @Operation(summary = "delete by id ")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        produitService.deleteProduit(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "delete by n ou tous id ")
    @DeleteMapping
    public ResponseEntity<Void> deleteByIdsOrAll(
            @RequestBody List<Long> ids
    ) {
        if (ids == null) {
            return ResponseEntity.badRequest().build();
        }
        if (ids.isEmpty()) {
            produitService.deleteAllProduits();
        } else {
            produitService.deleteProduitsByIds(ids);
        }
        return ResponseEntity.noContent().build();
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


    @Operation(summary = "Get un barcode by id")
    @GetMapping(path = "/code/{id}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<Resource> getBarcodeById(@PathVariable Long id) throws IOException {
        Resource img = produitService.loadBarcodeImage(id);
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(img);
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
