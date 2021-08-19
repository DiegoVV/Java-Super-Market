package com.diego.SuperMarket.service.impl;

import com.diego.SuperMarket.entity.History;
import com.diego.SuperMarket.entity.Inventory;
import com.diego.SuperMarket.entity.Product;
import com.diego.SuperMarket.repository.HistoryRepository;
import com.diego.SuperMarket.service.HistoryService;
import com.diego.SuperMarket.service.InventoryService;
import com.diego.SuperMarket.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService {

    private final HistoryRepository historyRepository;
    private final ProductService productService;
    private final InventoryService inventoryService;

    @Override
    public List<History> getHistory() {
        return historyRepository.findAll();
    }

    @Override
    public History getHistory(Long id) {
        Optional<History> history = historyRepository.findById(id);

        return history.orElseThrow(NoSuchElementException::new);
    }

    @Override
    public History addHistory(History history) {

        List<Inventory> inventories = inventoryService.getInventory();

        inventories = inventories.stream().filter(inv -> (history.getProductId() == (inv.getProduct() == null ? null : inv.getProduct().getId()))).collect(Collectors.toList());

        if (inventories.isEmpty()) {
            throw new IndexOutOfBoundsException("Trying to create history of product without inventory");
        }

        Inventory inventory = inventories.get(0);

        Inventory updatedInventory = new Inventory(inventory.getProduct(), inventory.getQuantity() - history.getQuantity());
        inventory = inventoryService.updateInventory(inventory.getId(), updatedInventory);

        return historyRepository.save(history);
    }

    @Override
    public History updateHistory(Long id, History history) {
        History updatedHistory = getHistory(id);

        if(history.getDate() != null){
            updatedHistory.setDate(history.getDate());
        }

        if(history.getQuantity() != null){
            updatedHistory.setQuantity(history.getQuantity());
        }

        if(history.getProductId() != null){
            updatedHistory.setProductId(history.getProductId());
        }

        if(history.getTotal() != null){
            updatedHistory.setTotal(history.getTotal());
        } else {
            Product product = getHistoryProduct(id);
            Float total = product.getPrice() * updatedHistory.getQuantity();
            updatedHistory.setTotal(total);
        } // This might result in a NoSuchElement error if the product doesn't exist.
        // We could divide the previous total by the quantity to get the previous price
        // and multiply that by the new quantity instead.

        return addHistory(updatedHistory);
    }

    @Override
    public ResponseEntity<Map<String, Boolean>> deleteHistory(Long id) {
        History history = getHistory(id);

        historyRepository.delete(history);
        Map<String, Boolean> response = new HashMap<>();
        response.put(String.format("History of id %d deleted", id), Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    @Override
    public void deleteHistory() {
        historyRepository.deleteAll();
    }

    @Override
    public Product getHistoryProduct(Long historyId) {
        Long productId = getHistory(historyId).getProductId();

        return productService.getProduct(productId);
    }
}
