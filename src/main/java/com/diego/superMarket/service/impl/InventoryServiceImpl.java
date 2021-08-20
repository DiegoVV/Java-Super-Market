package com.diego.superMarket.service.impl;

import com.diego.superMarket.entity.Inventory;
import com.diego.superMarket.repository.InventoryRepository;
import com.diego.superMarket.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
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
// Tried checking if the product already had an existing inventory, but resulted in InvalidDataAccessApiUsageException exception
//        List<Inventory> inventories = getInventory();
//
//        if(!inventories.isEmpty()) {
//            inventories = inventories.stream().filter(inv -> (inventory.getProduct().getId() == (inv.getProduct() == null ? null : inv.getProduct().getId()))).collect(Collectors.toList());
//        }
//
//        if(inventories.isEmpty()){
//            return inventoryRepository.save(inventory);
//        } else {
//            throw new IllegalArgumentException("That product already has an inventory");
//        }
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
