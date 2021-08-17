package com.diego.SuperMarket.service.impl;

import com.diego.SuperMarket.entity.Inventory;
import com.diego.SuperMarket.repository.InventoryRepository;
import com.diego.SuperMarket.service.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    @Override
    public List<Inventory> getInventory() {
        return inventoryRepository.findAll();
    }

    @Override
    public Inventory getInventory(Long id) {
        Optional<Inventory> inventory = inventoryRepository.findById(id);

        return inventory.orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Inventory addInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    @Override
    public Inventory updateInventory(Long id, Inventory inventory) {
        Inventory updatedInventory = getInventory(id);

        updatedInventory.setProduct(inventory.getProduct());
        updatedInventory.setQuantity(inventory.getQuantity());

        return addInventory(updatedInventory);
    }

    @Override
    public ResponseEntity<Map<String, Boolean>> deleteInventory(Long id) {
        Inventory inventory = getInventory(id);

        inventoryRepository.delete(inventory);
        Map<String, Boolean> response = new HashMap<>();
        response.put(String.format("Inventory of id %d deleted", id), Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    @Override
    public void deleteInventory() {
        inventoryRepository.deleteAll();
    }
}
