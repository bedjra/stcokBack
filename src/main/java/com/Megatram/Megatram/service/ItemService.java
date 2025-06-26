//package com.Megatram.Megatram.service;
//
//
//import com.Megatram.Megatram.Dto.ItemDto;
//import com.Megatram.Megatram.Entity.Items;
//import com.Megatram.Megatram.Entity.Produit;
//import com.Megatram.Megatram.repository.ItemsRepository;
//import com.Megatram.Megatram.repository.ProduitRepos;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class ItemService {
//
//    private final ItemsRepository itemRepository;
//    private final ProduitRepos produitRepository;
//
//    public ItemService(ItemsRepository itemRepository, ProduitRepos produitRepository) {
//        this.itemRepository = itemRepository;
//        this.produitRepository = produitRepository;
//    }
//
//    public ItemDto createItem(ItemDto dto) {
//        Produit produit = produitRepository.findById(dto.getProduitId())
//                .orElseThrow(() -> new RuntimeException("Produit non trouvé"));
//
//        Items item = new Items();
//        item.setProduit(produit);
//        item.setQuantite(dto.getQuantite());
//        item.setPourcentage(dto.getPourcentage());
//
//        // Calcul automatique du prix total
//        double prixTotal = produit.getPrix() * dto.getQuantite() * dto.getPourcentage() / 100.0;
//        item.setPrixTotal(prixTotal);
//
//        item = itemRepository.save(item);
//        return convertToDto(item);
//    }
//
//    public List<ItemDto> getAllItems() {
//        return itemRepository.findAll().stream()
//                .map(this::convertToDto)
//                .collect(Collectors.toList());
//    }
//
//    public ItemDto getItemById(Long id) {
//        Items item = itemRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Item non trouvé"));
//        return convertToDto(item);
//    }
//
//    public ItemDto updateItem(Long id, ItemDto dto) {
//        Items item = itemRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Item non trouvé"));
//
//        Produit produit = produitRepository.findById(dto.getProduitId())
//                .orElseThrow(() -> new RuntimeException("Produit non trouvé"));
//
//        item.setProduit(produit);
//        item.setQuantite(dto.getQuantite());
//        item.setPourcentage(dto.getPourcentage());
//
//        double prixTotal = produit.getPrix() * dto.getQuantite() * dto.getPourcentage() / 100.0;
//        item.setPrixTotal(prixTotal);
//
//        item = itemRepository.save(item);
//        return convertToDto(item);
//    }
//
//    public void deleteItem(Long id) {
//        itemRepository.deleteById(id);
//    }
//
//    private ItemDto convertToDto(Items item) {
//        ItemDto dto = new ItemDto();
//        dto.setId(item.getId());
//        dto.setProduitId(item.getProduit().getId());
//        dto.setQuantite(item.getQuantite());
//        dto.setPourcentage(item.getPourcentage());
//        dto.setProduitPrix(item.getProduit().getPrix());
//        dto.setPrixTotal(item.getPrixTotal());
//        return dto;
//    }
//}
