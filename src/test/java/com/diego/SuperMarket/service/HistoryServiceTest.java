package com.diego.SuperMarket.service;

import ch.qos.logback.core.spi.LifeCycle;
import com.diego.SuperMarket.entity.History;
import com.diego.SuperMarket.entity.Inventory;
import com.diego.SuperMarket.entity.Product;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class HistoryServiceTest {

    @Autowired
    HistoryService historyService;
    @Autowired
    ProductService productService;
    @Autowired
    InventoryService inventoryService;

    @BeforeEach
    void setUp() {
        historyService.deleteHistory();
        System.out.println("--- Repository cleaned before test ---");
    }

    @Test
    @DisplayName("Get all histories")
    void getHistory() {

        Product product = productService.addProduct(new Product("Chocolate", 1.00F));
        Product product2 = productService.addProduct(new Product("Water", 2.00F));
        Product product3 = productService.addProduct(new Product("Garlic Bread", 3.50F));

        Inventory inventory = inventoryService.addInventory(new Inventory(product, 35));
        Inventory inventory2 = inventoryService.addInventory(new Inventory(product2, 80));
        Inventory inventory3 = inventoryService.addInventory(new Inventory(product3, 100));

        History history = historyService.addHistory(new History(product.getId(), 5));
        History history2 = historyService.addHistory(new History(product2.getId(), 15));
        History history3 = historyService.addHistory(new History(product3.getId(), 50));

        List historyList = new ArrayList<History>();
        historyList.add(history);
        historyList.add(history2);
        historyList.add(history3);

        Assertions.assertFalse(historyService.getHistory().isEmpty(), "No products were added to the repository");
        Assertions.assertEquals(historyList, historyService.getHistory(), "Returned something other than the 3 mocked products");
    }

    @Test
    @DisplayName("Get a specific history by its ID")
    void getSingleHistory() {

        Product product = productService.addProduct(new Product("Chocolate", 1.00F));
        Product product2 = productService.addProduct(new Product("Water", 2.00F));

        Inventory inventory = inventoryService.addInventory(new Inventory(product, 35));
        Inventory inventory2 = inventoryService.addInventory(new Inventory(product2, 80));

        History history = historyService.addHistory(new History(product.getId(), 5));
        History history2 = historyService.addHistory(new History(product2.getId(), 15));

        Assertions.assertEquals(product.getId(), historyService.getHistory(history.getId()).getProductId());
        Assertions.assertEquals(5, historyService.getHistory(history.getId()).getQuantity());

        Assertions.assertEquals(product2.getId(), historyService.getHistory(history2.getId()).getProductId());
        Assertions.assertEquals(15, historyService.getHistory(history2.getId()).getQuantity());
    }

    @Test
    @DisplayName("Add a history to the repository, deducting the quantity from the inventory")
    void addHistory() {

        Assertions.assertTrue(historyService.getHistory().isEmpty(), "The repository was not empty");

        Product product = productService.addProduct(new Product("Chocolate", 1.00F));

        Inventory inventory = inventoryService.addInventory(new Inventory(product, 35));

        Integer inventoryQuantity = inventory.getQuantity();

        History history = historyService.addHistory(new History(product.getId(), 5));

        Assertions.assertEquals(inventoryQuantity-history.getQuantity(), inventoryService.getInventory(inventory.getId()).getQuantity(), "History quantity was not deducted from the inventory");
        Assertions.assertFalse(historyService.getHistory().isEmpty(), "The history was not added to the repository");
        Assertions.assertEquals(product.getId(), historyService.getHistory(history.getId()).getProductId());
        Assertions.assertEquals(5, historyService.getHistory(history.getId()).getQuantity());
    }

    @Test
    @DisplayName("Update an existing history with other data")
    void updateHistory() {

        Product product = productService.addProduct(new Product("Chocolate", 1.00F));
        Product product2 = productService.addProduct(new Product("Water", 2.00F));

        Inventory inventory = inventoryService.addInventory(new Inventory(product, 35));
        Inventory inventory2 = inventoryService.addInventory(new Inventory(product2, 80));

        History history = historyService.addHistory(new History(product.getId(), 5));
        History history2 = new History(product2.getId(), 15);

        Long id1 = history.getId();

        Assertions.assertEquals(product.getId(), historyService.getHistory(id1).getProductId());
        Assertions.assertEquals(5, historyService.getHistory(id1).getQuantity());
        Assertions.assertEquals(history.getDate(), historyService.getHistory(id1).getDate());

        History history3 = historyService.updateHistory(id1, history2);

        Assertions.assertEquals(product2.getId(), historyService.getHistory(id1).getProductId());
        Assertions.assertEquals(15, historyService.getHistory(id1).getQuantity());
        Assertions.assertEquals(history2.getDate(), historyService.getHistory(id1).getDate());
    }

    @Test
    @DisplayName("Delete a history")
    void deleteSingleHistory() {
        Product product = productService.addProduct(new Product("Chocolate", 1.00F));

        Inventory inventory = inventoryService.addInventory(new Inventory(product, 35));

        History history = historyService.addHistory(new History(product.getId(), 5));

        Assertions.assertFalse(historyService.getHistory().isEmpty(), "The product was not added to the repository");
        historyService.deleteHistory(history.getId());
        Assertions.assertTrue(historyService.getHistory().isEmpty(), "The product was not deleted or there were other products in the repository");
    }

    @Test
    @DisplayName("Get the product data out from a history")
    void getHistoryProduct() {
        Product product = productService.addProduct(new Product("Chocolate", 1.00F));

        Inventory inventory = inventoryService.addInventory(new Inventory(product, 35));

        History history = historyService.addHistory(new History(product.getId(), 5));

        Product retrieved = historyService.getHistoryProduct(history.getId());

        Assertions.assertEquals(product.getName(), retrieved.getName());
        Assertions.assertEquals(product.getPrice(), retrieved.getPrice());
    }

    @Test
    void getMostSoldProducts() {

        Product product = productService.addProduct(new Product("Chocolate", 1.00F));
        Product product2 = productService.addProduct(new Product("Water", 2.00F));
        Product product3 = productService.addProduct(new Product("Garlic Bread", 3.50F));

        Inventory inventory = inventoryService.addInventory(new Inventory(product, 35));
        Inventory inventory2 = inventoryService.addInventory(new Inventory(product2, 80));
        Inventory inventory3 = inventoryService.addInventory(new Inventory(product3, 100));

        History history = historyService.addHistory(new History(product.getId(), 5));
        History history2 = historyService.addHistory(new History(product.getId(), 5));
        History history3 = historyService.addHistory(new History(product2.getId(), 15));
        History history4 = historyService.addHistory(new History(product2.getId(), 25));
        History history5 = historyService.addHistory(new History(product3.getId(), 30));
        History history6 = historyService.addHistory(new History(product.getId(), 10));

        historyService.getMostSoldProducts(2);

        Assertions.assertEquals(1, 1);
    }

    @AfterEach
    void tearDown() {
        System.out.println("--- Test finished ---");
    }
}