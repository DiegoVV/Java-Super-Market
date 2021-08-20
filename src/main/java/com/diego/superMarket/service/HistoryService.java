package com.diego.superMarket.service;

import com.diego.superMarket.entity.History;
import com.diego.superMarket.entity.Product;
import org.springframework.http.ResponseEntity;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface HistoryService {

    List<History> getHistory();

    History getHistory(Long id);

    History addHistory(History history);

    History updateHistory(Long id, History history);

    ResponseEntity<Map<String, Boolean>> deleteHistory(Long id);

    void deleteHistory();

    Product getHistoryProduct(Long historyId);

    LinkedHashMap<Product, Integer> getMostSoldProducts(Integer amount);
}
