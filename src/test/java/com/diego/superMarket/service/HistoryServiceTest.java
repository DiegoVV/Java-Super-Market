package com.diego.superMarket.service;

import com.diego.superMarket.entity.History;
import com.diego.superMarket.entity.Inventory;
import com.diego.superMarket.entity.Product;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

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
    @DisplayName("Accurately report products with the highest quantity of sales")
    void getMostSoldProducts() {

        Integer productsOnReport = 4;

        Product product = productService.addProduct(new Product("Chocolate", 1.00F));
        Product product2 = productService.addProduct(new Product("Water", 2.00F));
        Product product3 = productService.addProduct(new Product("Garlic Bread", 3.50F));
        Product product4 = productService.addProduct(new Product("Soda", 1.50F));
        Product product5 = productService.addProduct(new Product("Juice", 2.00F));
        Product product6 = productService.addProduct(new Product("Candy", 0.50F));

        Inventory inventory = inventoryService.addInventory(new Inventory(product, 35));
        Inventory inventory2 = inventoryService.addInventory(new Inventory(product2, 80));
        Inventory inventory3 = inventoryService.addInventory(new Inventory(product3, 100));
        Inventory inventory4 = inventoryService.addInventory(new Inventory(product4, 90));
        Inventory inventory5 = inventoryService.addInventory(new Inventory(product5, 70));
        Inventory inventory6 = inventoryService.addInventory(new Inventory(product6, 500));

        History history = historyService.addHistory(new History(product.getId(), 5));
        History history2 = historyService.addHistory(new History(product.getId(), 5));
        History history3 = historyService.addHistory(new History(product2.getId(), 15));
        History history4 = historyService.addHistory(new History(product2.getId(), 25));
        History history5 = historyService.addHistory(new History(product3.getId(), 30));
        History history6 = historyService.addHistory(new History(product.getId(), 10));
        History history7 = historyService.addHistory(new History(product6.getId(), 105));
        History history8 = historyService.addHistory(new History(product4.getId(), 20));
        History history9 = historyService.addHistory(new History(product5.getId(), 1));

        LinkedHashMap<Product, Integer> mostSoldProducts = historyService.getMostSoldProducts(productsOnReport);

        Assertions.assertTrue(productsOnReport >= mostSoldProducts.size(), "Report contained wrong amount of items");

        Iterator<Map.Entry<Product, Integer>> iterator = mostSoldProducts.entrySet().iterator();
        Assertions.assertEquals(product6.getId(), iterator.next().getKey().getId(), "Unexpected product listed as with most sales");


    }

    @AfterEach
    void tearDown() {
        historyService.deleteHistory();
        inventoryService.deleteInventory();
        productService.deleteProduct();
        System.out.println("--- Test finished ---");
    }
}