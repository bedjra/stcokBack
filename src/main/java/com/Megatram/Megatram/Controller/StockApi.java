package com.Megatram.Megatram.Controller;


import com.Megatram.Megatram.service.StockService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;

@RestController
@RequestMapping("/api/Stock")
@Tag(name = "Stock", description = "Gestion des Stock")
@CrossOrigin(origins = "http://localhost:3000")

public class StockApi {

    @Autowired
    private StockService stockService;

//    @GetMapping("/etat")
//    public ResponseEntity<Integer> getEtatStock(
//            @RequestParam Long produitId,
//            @RequestParam Long entrepotId,
//            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
//    ) {
//        Date dateLimite = java.sql.Date.valueOf(date);
//        int etatStock = stockService.getEtatStock(produitId, entrepotId, dateLimite);
//        return ResponseEntity.ok(etatStock);
//    }
//
//    @GetMapping("/etat-global")
//    public ResponseEntity<Integer> getTotalStockEntrepotAUneDate(
//            @RequestParam Long entrepotId,
//            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
//    ) {
//        Date dateLimite = java.sql.Date.valueOf(date);
//        int total = stockService.getTotalStockDansEntrepotAUneDate(entrepotId, dateLimite);
//        return ResponseEntity.ok(total);
//    }



//    @GetMapping
//    public List<StockDto> getAllStocks() {
//        return stockService.getAllStocks();
//    }
//
//    @GetMapping("/{id}")
//    public StockDto getStockById(@PathVariable Long id) {
//        return stockService.getStockById(id);
//    }
//
//    @PostMapping
//    public StockDto createStock(@RequestBody StockDto dto) {
//        return stockService.createStock(dto);
//    }
//
//    @PutMapping("/{id}")
//    public StockDto updateStock(@PathVariable Long id, @RequestBody StockDto dto) {
//        return stockService.updateStock(id, dto);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteStock(@PathVariable Long id) {
//        stockService.deleteStock(id);
//    }
//
}
