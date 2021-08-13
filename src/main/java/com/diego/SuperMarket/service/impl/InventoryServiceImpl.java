package com.diego.SuperMarket.service.impl;

import com.diego.SuperMarket.entity.Inventory;
import com.diego.SuperMarket.repository.InventoryRepository;
import com.diego.SuperMarket.service.InventoryService;
import lombok.AllArgsConstructor;
import org.hibernate.PropertyNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
        return inventoryRepository.findById(id)
                .orElseThrow(() -> new PropertyNotFoundException("Inventory with ID " + id + " does not exist"));
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
