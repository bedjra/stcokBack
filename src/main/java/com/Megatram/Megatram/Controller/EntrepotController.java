package com.Megatram.Megatram.Controller;


import com.Megatram.Megatram.Dto.EntrepotDto;
import com.Megatram.Megatram.Entity.Entrepot;
import com.Megatram.Megatram.service.EntrepotService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entrepot")
@Tag(name = "Entrepot", description = "Gestion des Categoriesentrepot")
public class EntrepotController {

    @Autowired
    private EntrepotService entrepotService;

    @Operation(summary = "all ")
    @GetMapping
    public ResponseEntity<List<EntrepotDto>> getAll() {
        return ResponseEntity.ok(entrepotService.getAll());
    }

    @Operation(summary = "get by id ")
    @GetMapping("/{id}")
    public ResponseEntity<EntrepotDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(entrepotService.getById(id));
    }

    @Operation(summary = "add ")
    @PostMapping
    public ResponseEntity<Entrepot> create(@RequestBody Entrepot entrepot) {
        return ResponseEntity.ok(entrepotService.saveEntrepot(entrepot));
    }

    @Operation(summary = "put ")
    @PutMapping("/{id}")
    public ResponseEntity<Entrepot> update(@PathVariable Long id, @RequestBody Entrepot entrepot) {
        entrepot.setId(id);
        return ResponseEntity.ok(entrepotService.saveEntrepot(entrepot));
    }

    // 🔴 Suppression
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        entrepotService.delete(id);
        return ResponseEntity.noContent().build();
    }
}