package com.diego.superMarket.service;

import com.diego.superMarket.entity.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface ProductService {

    List<Product> getProduct();

    Product getProduct(Long id);

    Product addProduct(Product product);

    Product updateProduct(Long id, Product product);

    ResponseEntity<Map<String, Boolean>> deleteProduct(Long id);

    void deleteProduct();
}
