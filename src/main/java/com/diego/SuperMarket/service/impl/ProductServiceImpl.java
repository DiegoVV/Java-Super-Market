package com.diego.SuperMarket.service.impl;

import com.diego.SuperMarket.entity.Inventory;
import com.diego.SuperMarket.entity.Product;
import com.diego.SuperMarket.repository.InventoryRepository;
import com.diego.SuperMarket.repository.ProductRepository;
import com.diego.SuperMarket.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public List<Product> getProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);

        return product.orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        return null;
    }

    @Override
    public ResponseEntity<Map<String, Boolean>> deleteProduct(Long id) {
        return null;
    }
}
