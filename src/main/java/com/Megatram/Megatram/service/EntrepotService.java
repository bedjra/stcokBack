package com.Megatram.Megatram.service;


import com.Megatram.Megatram.Dto.EntrepotDto;
import com.Megatram.Megatram.Entity.Entrepot;
import com.Megatram.Megatram.repository.EntrepotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EntrepotService {

    @Autowired
    private EntrepotRepository entrepotRepository;

    // 🟢 Create or Update
    public Entrepot saveEntrepot(Entrepot entrepot) {
        return entrepotRepository.save(entrepot);
    }

    // 🔵 Read All
    public List<EntrepotDto> getAll() {
        return entrepotRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    // 🔵 Read One by ID
    public EntrepotDto getById(Long id) {
        Entrepot entrepot = entrepotRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entrepôt introuvable avec ID : " + id));
        return toDto(entrepot);
    }

    // 🔴 Delete
    public void delete(Long id) {
        entrepotRepository.deleteById(id);
    }

    // 🟡 Mapping Entité → DTO
    private EntrepotDto toDto(Entrepot e) {
        EntrepotDto dto = new EntrepotDto();
        dto.setId(e.getId());
        dto.setNom(e.getNom());
        dto.setRef(e.getRef());
        dto.setQuantite(e.getQuantite());
        dto.setValeurVente(e.getValeurVente());
        return dto;
    }
}