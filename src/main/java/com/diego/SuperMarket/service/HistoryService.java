package com.diego.SuperMarket.service;

import com.diego.SuperMarket.entity.History;
import com.diego.SuperMarket.entity.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface HistoryService {

    List<History> getHistory();

    History getHistory(Long id);

    History addHistory(History history);

    History updateHistory(Long id, History history);

    ResponseEntity<Map<String, Boolean>> deleteHistory(Long id);

    void deleteHistory();

    Product getHistoryProduct(Long productId);
}
