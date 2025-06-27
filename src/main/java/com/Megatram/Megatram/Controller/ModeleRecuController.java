package com.Megatram.Megatram.Controller;


import com.Megatram.Megatram.Entity.ModeleRecu;
import com.Megatram.Megatram.service.ModeleRcuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Modele")
@Tag(name = "Facture", description = "CRUD pour l'entité Facture")
@CrossOrigin(origins = "http://localhost:3000")

public class ModeleRecuController {

    private final ModeleRcuService modeleRcuService;

    public ModeleRecuController(ModeleRcuService modeleRcuService) {
        this.modeleRcuService = modeleRcuService;
    }

    @Operation(summary = "Créer une facture")
    @PostMapping
    public ResponseEntity<ModeleRecu> createFacture(@RequestBody ModeleRecu modeleRecu) {
        return ResponseEntity.ok(modeleRcuService.saveFacture(modeleRecu));
    }

    @Operation(summary = "Liste de toutes les factures")
    @GetMapping
    public ResponseEntity<List<ModeleRecu>> getAllFactures() {
        return ResponseEntity.ok(modeleRcuService.getAllFactures());
    }

    @Operation(summary = "Obtenir une facture par ID")
    @GetMapping("/{id}")
    public ResponseEntity<ModeleRecu> getFactureById(@PathVariable String id) {
        return modeleRcuService.getFactureById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Mettre à jour une facture")
    @PutMapping("/{id}")
    public ResponseEntity<ModeleRecu> updateFacture(@PathVariable String id, @RequestBody ModeleRecu modeleRecu) {
        ModeleRecu updated = modeleRcuService.updateFacture(id, modeleRecu);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Supprimer une facture")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFacture(@PathVariable String id) {
        modeleRcuService.deleteFacture(id);
        return ResponseEntity.noContent().build();
    }
}
