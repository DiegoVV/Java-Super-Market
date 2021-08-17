package com.diego.SuperMarket.service.impl;

import com.diego.SuperMarket.entity.Product;
import com.diego.SuperMarket.repository.ProductRepository;
import com.diego.SuperMarket.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

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
        Product updateProduct = getProduct(id);

        updateProduct.setName(product.getName());
        updateProduct.setPrice(product.getPrice());

        return addProduct(updateProduct);
    }

    @Override
    public ResponseEntity<Map<String, Boolean>> deleteProduct(Long id) {
        Product product = getProduct(id);

        productRepository.delete(product);
        Map<String, Boolean> response = new HashMap<>();
        response.put(String.format("Product of id %d deleted", id), Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
