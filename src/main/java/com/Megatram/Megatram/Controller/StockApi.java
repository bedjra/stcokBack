//package com.Megatram.Megatram.Controller;
//
//
//import com.Megatram.Megatram.Dto.StockDto;
//import com.Megatram.Megatram.service.StockService;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@CrossOrigin(origins = "http://localhost:3000")
//@RestController
//@RequestMapping("/Stock")
//@Tag(name = "Stock", description = "Gestion des Stock")
//
//public class StockApi {
//
//    @Autowired
//    private StockService stockService;
//
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
//}
