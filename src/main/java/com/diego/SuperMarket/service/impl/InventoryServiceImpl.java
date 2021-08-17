package com.diego.SuperMarket.service.impl;

import com.diego.SuperMarket.entity.Inventory;
import com.diego.SuperMarket.exception.ElementNotFoundException;
import com.diego.SuperMarket.repository.InventoryRepository;
import com.diego.SuperMarket.service.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

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

//        if (!inventory.isPresent()) {
//            throw new ElementNotFoundException(String.format("Inventory of ID %d does not exist", id));
//        } else {
//            return inventory.get();
//        }
    }

    @Override
    public Inventory addInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    @Override
    public Inventory updateInventory(Long id, Inventory inventory) {
        return null;
    }

    @Override
    public ResponseEntity<Map<String, Boolean>> deleteInventory(Long id) {
        return null;
    }
}
