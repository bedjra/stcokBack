package com.Megatram.Megatram.service;


import com.Megatram.Megatram.Dto.ProduitDto;
import com.Megatram.Megatram.Entity.Produit;
import com.Megatram.Megatram.Utils.ProduitExcelImporter;
import com.Megatram.Megatram.repository.CategorieRep;
import com.Megatram.Megatram.repository.ProduitRepos;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class ProduitService {

    @Autowired
    private ProduitRepos produitRepository;

    @Autowired
    private CategorieRep categorieRepository;


    private final Path dossierBarcodes = Paths.get("barcodes");


    public void generateBarcodeImage(String barcodeText, String filename) throws Exception {
        if (!Files.exists(dossierBarcodes)) {
            Files.createDirectories(dossierBarcodes);
        }

        Path outputPath = dossierBarcodes.resolve(filename);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(barcodeText, BarcodeFormat.CODE_128, 300, 100);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", outputPath);
    }


    public List<Produit> importerProduitsDepuisExcel(MultipartFile file) {
        List<Produit> produits = ProduitExcelImporter.lireProduitsDepuisExcel(file, categorieRepository);

        for (Produit produit : produits) {
            try {
                // Génère un code unique basé sur la ref ou nom
                String code = "PROD-" + produit.getRef() + "-" + System.currentTimeMillis();
                produit.setCodeBarre(code);

                // Génère l’image du code-barres
                generateBarcodeImage(code, code + ".png");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return produitRepository.saveAll(produits);
    }


    public List<ProduitDto> getAllProduitsDto() {
        List<Produit> produits = produitRepository.findAll();

        return produits.stream()
                .map(p -> {
                    ProduitDto dto = new ProduitDto();
                    dto.setId(p.getId());
                    dto.setNom(p.getNom());
                    dto.setRef(p.getRef());
                    dto.setQte(p.getQte());
                    dto.setPrix(p.getPrix());
                    dto.setCodeBarre(p.getCodeBarre());
                    dto.setCategorieId(p.getCategorie() != null ? p.getCategorie().getId() : null);
                    return dto;
                })
                .toList();
    }



    public ResponseEntity<String> updateProduit(Long id, Produit updated) {
        return produitRepository.findById(id)
                .map(p -> {
                    p.setNom(updated.getNom());
                    p.setRef(updated.getRef());
                    p.setQte(updated.getQte());
                    p.setPrix(updated.getPrix());
                    p.setCategorie(updated.getCategorie());
                    produitRepository.save(p);
                    return ResponseEntity.ok("Produit mis à jour avec succès");
                })
                .orElse(ResponseEntity.notFound().build());
    }


    public ResponseEntity<String> deleteProduit(Long id) {
        if (produitRepository.existsById(id)) {
            produitRepository.deleteById(id);
            return ResponseEntity.ok("Produit supprimé");
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    public ResponseEntity<Produit> getProduitById(Long id) {
        return produitRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

