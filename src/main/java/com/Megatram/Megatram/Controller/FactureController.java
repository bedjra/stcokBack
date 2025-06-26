package com.Megatram.Megatram.Controller;


import com.Megatram.Megatram.Entity.Facture;
import com.Megatram.Megatram.service.FactureService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/factures")
@Tag(name = "Facture", description = "CRUD pour l'entité Facture")
public class FactureController {

    private final FactureService factureService;

    public FactureController(FactureService factureService) {
        this.factureService = factureService;
    }

    @Operation(summary = "Créer une facture")
    @PostMapping
    public ResponseEntity<Facture> createFacture(@RequestBody Facture facture) {
        return ResponseEntity.ok(factureService.saveFacture(facture));
    }

    @Operation(summary = "Liste de toutes les factures")
    @GetMapping
    public ResponseEntity<List<Facture>> getAllFactures() {
        return ResponseEntity.ok(factureService.getAllFactures());
    }

    @Operation(summary = "Obtenir une facture par ID")
    @GetMapping("/{id}")
    public ResponseEntity<Facture> getFactureById(@PathVariable String id) {
        return factureService.getFactureById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Mettre à jour une facture")
    @PutMapping("/{id}")
    public ResponseEntity<Facture> updateFacture(@PathVariable String id, @RequestBody Facture facture) {
        Facture updated = factureService.updateFacture(id, facture);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Supprimer une facture")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFacture(@PathVariable String id) {
        factureService.deleteFacture(id);
        return ResponseEntity.noContent().build();
    }
}
