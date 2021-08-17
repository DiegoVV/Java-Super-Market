package com.diego.SuperMarket.controller;

import com.diego.SuperMarket.entity.Inventory;
import com.diego.SuperMarket.service.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = "api/v1/inventories")
@AllArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;

    @GetMapping
    public List<Inventory> getInventory() {
        return inventoryService.getInventory();
    }

    @GetMapping("/{id}")
    public Inventory getInventory(@PathVariable("id") Long id) throws NoSuchElementException {
        return inventoryService.getInventory(id);
    }

    @PostMapping
    public Inventory addInventory(@RequestBody Inventory inventory) {
        return inventoryService.addInventory(inventory);
    }

    @PutMapping("/{id}")
    public Inventory updateInventory(@PathVariable("id") Long id, @RequestBody Inventory inventory) {
        return inventoryService.updateInventory(id, inventory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteInventory(@PathVariable("id") Long id){
        return inventoryService.deleteInventory(id);
    }
}
