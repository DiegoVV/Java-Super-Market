package com.diego.SuperMarket.service;

import com.diego.SuperMarket.entity.Inventory;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface InventoryService {
    List<Inventory> getInventory();

    Inventory getInventory(Long id);

    Inventory addInventory(Inventory inventory);

    Inventory updateInventory(Long id, Inventory inventory);

    ResponseEntity<Map<String, Boolean>> deleteInventory(Long id);
}
