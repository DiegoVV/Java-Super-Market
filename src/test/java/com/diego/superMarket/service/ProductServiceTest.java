package com.diego.superMarket.service;

import com.diego.superMarket.entity.Product;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @BeforeEach
    void setUp() {
        productService.deleteProduct();
        System.out.println("--- Repository cleaned before test ---");
    }

    @Test
    @DisplayName("Get all products")
    void getProduct() {

        Product product = productService.addProduct(new Product("Chocolate", 1.00F));
        Product product2 = productService.addProduct(new Product("Water", 2.00F));
        Product product3 = productService.addProduct(new Product("Garlic Bread", 3.50F));

        List productList = new ArrayList<Product>();
        productList.add(product);
        productList.add(product2);
        productList.add(product3);

        Assertions.assertFalse(productService.getProduct().isEmpty(), "No products were added to the repository");
        Assertions.assertEquals(productList, productService.getProduct(), "Returned something other than the 3 mocked products");
    }

    @Test
    @DisplayName("Get a specific product")
    void getSingleProduct() {

        Long id1 = productService.addProduct(new Product("Chocolate", 1.00F)).getId();
        Long id2 = productService.addProduct(new Product("Water", 2.00F)).getId();

        Assertions.assertEquals(1.00F,productService.getProduct(id1).getPrice());
        Assertions.assertEquals("Chocolate",productService.getProduct(id1).getName());

        Assertions.assertEquals(2F,productService.getProduct(id2).getPrice());
        Assertions.assertEquals("Water",productService.getProduct(id2).getName());
    }

    @Test
    @DisplayName("Adding a product")
    void addProduct() {

        Assertions.assertTrue(productService.getProduct().isEmpty(), "The repository was not empty");

        Product product = productService.addProduct(new Product("Chocolate", 1.00F));

        Assertions.assertFalse(productService.getProduct().isEmpty(), "The product was not added to the repository");
        Assertions.assertEquals(1F,productService.getProduct(product.getId()).getPrice());
        Assertions.assertEquals("Chocolate",productService.getProduct(product.getId()).getName());
    }

    @Test
    @DisplayName("Updating product info")
    void updateProduct() {
        Product product = new Product("Chocolate", 1.00F);
        Product product2 = new Product("Water", 2.00F);

        Long id1 = productService.addProduct(product).getId();

        Assertions.assertEquals(1F,productService.getProduct(id1).getPrice());
        Assertions.assertEquals("Chocolate",productService.getProduct(id1).getName());

        Product product3 = productService.updateProduct(id1, product2);

        Assertions.assertEquals(2F,productService.getProduct(id1).getPrice());
        Assertions.assertEquals("Water",productService.getProduct(id1).getName());
    }

    @Test
    @DisplayName("Deleting a product")
    void deleteProduct() {
        Product product = productService.addProduct(new Product("Chocolate", 1.00F));

        Assertions.assertFalse(productService.getProduct().isEmpty(), "The product was not added to the repository");
        productService.deleteProduct(product.getId());
        Assertions.assertTrue(productService.getProduct().isEmpty(), "The product was not deleted or there were other products in the repository");
    }

    @AfterEach
    void tearDown() {
        System.out.println("--- Test finished ---");
    }
}