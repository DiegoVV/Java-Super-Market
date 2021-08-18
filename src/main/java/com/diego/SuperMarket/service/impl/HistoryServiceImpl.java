package com.diego.SuperMarket.service.impl;

import com.diego.SuperMarket.entity.History;
import com.diego.SuperMarket.entity.Inventory;
import com.diego.SuperMarket.entity.Product;
import com.diego.SuperMarket.repository.HistoryRepository;
import com.diego.SuperMarket.repository.InventoryRepository;
import com.diego.SuperMarket.repository.ProductRepository;
import com.diego.SuperMarket.service.HistoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class HistoryServiceImpl implements HistoryService {

    private final HistoryRepository historyRepository;
    private final ProductRepository productRepository;

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
            Product product = getHistoryProduct(updatedHistory.getProductId());
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
        Optional<Product> product = productRepository.findById(productId);

        return product.orElseThrow(NoSuchElementException::new);
    }
}
