package com.diego.superMarket.service;

import com.diego.superMarket.entity.Inventory;
import com.diego.superMarket.entity.Product;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class InventoryServiceTest {

    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private ProductService productService;

    @BeforeEach
    void setUp() {
        inventoryService.deleteInventory();
        System.out.println("--- Repository cleaned before test ---");
    }

    @Test
    @DisplayName("Get all inventories")
    void getInventory() {

        Product product = productService.addProduct(new Product("Chocolate", 1.00F));
        Product product2 = productService.addProduct(new Product("Water", 2.00F));
        Product product3 = productService.addProduct(new Product("Garlic Bread", 3.50F));

        Inventory inventory = inventoryService.addInventory(new Inventory(product, 35));
        Inventory inventory2 = inventoryService.addInventory(new Inventory(product2, 80));
        Inventory inventory3 = inventoryService.addInventory(new Inventory(product3, 100));

        List inventoryList = new ArrayList<Inventory>();
        inventoryList.add(inventory);
        inventoryList.add(inventory2);
        inventoryList.add(inventory3);

        Assertions.assertFalse(inventoryService.getInventory().isEmpty(), "No inventories were returned");
        Assertions.assertEquals(inventoryList, inventoryService.getInventory(), "Returned something other than the 3 mocked inventories");
    }

    @Test
    @DisplayName("Get a specific inventory")
    void getSingleInventory() {

        Product product = productService.addProduct(new Product("Chocolate", 1.00F));
        Product product2 = productService.addProduct(new Product("Water", 2.00F));

        Inventory inventory = inventoryService.addInventory(new Inventory(product, 35));
        Inventory inventory2 = inventoryService.addInventory(new Inventory(product2, 80));

        Assertions.assertEquals(inventory.getQuantity(),inventoryService.getInventory(inventory.getId()).getQuantity());
        Assertions.assertEquals(product.getName(),inventoryService.getInventory(inventory.getId()).getProduct().getName());

        Assertions.assertEquals(inventory2.getQuantity(),inventoryService.getInventory(inventory2.getId()).getQuantity());
        Assertions.assertEquals(product2.getName(),inventoryService.getInventory(inventory2.getId()).getProduct().getName());
    }

    @Test
    @DisplayName("Adding an inventory")
    void addInventory() {

        Assertions.assertTrue(inventoryService.getInventory().isEmpty(), "The repository was not empty");

        Product product = productService.addProduct(new Product("Chocolate", 1.00F));

        Inventory inventory = inventoryService.addInventory(new Inventory(product, 35));

        Assertions.assertFalse(inventoryService.getInventory().isEmpty(), "The inventory was not added to the repository");

        Assertions.assertEquals(inventory.getQuantity(),inventoryService.getInventory(inventory.getId()).getQuantity());
        Assertions.assertEquals(product.getName(),inventoryService.getInventory(inventory.getId()).getProduct().getName());
    }

    @Test
    @DisplayName("Updating inventory info")
    void updateInventory() {

        Product product = productService.addProduct(new Product("Chocolate", 1.00F));
        Product product2 = productService.addProduct(new Product("Water", 2.00F));

        Inventory inventory = inventoryService.addInventory(new Inventory(product, 35));
        Inventory inventory2 = new Inventory(product2, 80);

        Assertions.assertEquals(inventory.getQuantity(),inventoryService.getInventory(inventory.getId()).getQuantity());
        Assertions.assertEquals(product.getName(),inventoryService.getInventory(inventory.getId()).getProduct().getName());

        Inventory inventory3 = inventoryService.updateInventory(inventory.getId(), inventory2);

        Assertions.assertEquals(inventory2.getQuantity(),inventoryService.getInventory(inventory.getId()).getQuantity());
        Assertions.assertEquals(product2.getName(),inventoryService.getInventory(inventory.getId()).getProduct().getName());
    }

    @Test
    @DisplayName("Deleting an inventory")
    void deleteInventory() {

        Product product = productService.addProduct(new Product("Chocolate", 1.00F));

        Inventory inventory = inventoryService.addInventory(new Inventory(product, 35));

        Assertions.assertFalse(inventoryService.getInventory().isEmpty(), "The inventory was not added to the repository");
        inventoryService.deleteInventory(inventory.getId());
        Assertions.assertTrue(inventoryService.getInventory().isEmpty(), "The inventory was not deleted or there are other inventories inside the repository");
    }

    @AfterEach
    void tearDown() {
        inventoryService.deleteInventory();
        productService.deleteProduct();
        System.out.println("--- Test finished ---");
    }
}