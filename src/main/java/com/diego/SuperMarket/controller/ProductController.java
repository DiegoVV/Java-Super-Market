package com.diego.SuperMarket.controller;

import com.diego.SuperMarket.entity.Product;
import com.diego.SuperMarket.service.impl.ProductServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = "api/v1/products")
@AllArgsConstructor
public class ProductController {
    private final ProductServiceImpl productService;

    @GetMapping
    public List<Product> getProduct() {
        return productService.getProduct();
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable("id") Long id) throws NoSuchElementException {
        return productService.getProduct(id);
    }

    @PostMapping
    public Product addInventory(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @PutMapping("/{id}")
    public Product updateInventory(@PathVariable("id") Long id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteProduct(@PathVariable("id") Long id){
        return productService.deleteProduct(id);
    }
}
