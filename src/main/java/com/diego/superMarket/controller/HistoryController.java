package com.diego.superMarket.controller;

import com.diego.superMarket.entity.History;
import com.diego.superMarket.entity.Product;
import com.diego.superMarket.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = "api/v1/histories")
@RequiredArgsConstructor
public class HistoryController {

    private final HistoryService historyService;

    @GetMapping
    public List<History> getHistory() {
        return historyService.getHistory();
    }

    @GetMapping("/{id}")
    public History getHistory(@PathVariable("id") Long id) throws NoSuchElementException {
        return historyService.getHistory(id);
    }

    @PostMapping
    public History addHistory(@RequestBody History history) {
        return historyService.addHistory(history);
    }

    @PutMapping("/{id}")
    public History updateHistory(@PathVariable("id") Long id, @RequestBody History history) {
        return historyService.updateHistory(id, history);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteHistory(@PathVariable("id") Long id) {
        return historyService.deleteHistory(id);
    }

    @DeleteMapping
    public void deleteHistory() {
        historyService.deleteHistory();
    }

    @GetMapping("{historyId}/product")
    public Product getHistoryProduct(@PathVariable("historyId") Long historyId) {
        return historyService.getHistoryProduct(historyId);
    }

    @GetMapping("/reports/mostsales/{amount}")
    public LinkedHashMap<Product, Integer> getMostSoldProducts(@PathVariable("amount") Integer amount) {
        return historyService.getMostSoldProducts(amount);
    }
}
