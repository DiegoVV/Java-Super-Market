package com.diego.SuperMarket.service;

import com.diego.SuperMarket.entity.History;
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

    @BeforeEach
    void setUp() {
        historyService.deleteHistory();
        System.out.println("--- Repository cleaned before test ---");
    }

    @Test
    @DisplayName("Get all histories")
    void getHistory() {

        History history = historyService.addHistory(new History(1L, 5));
        History history2 = historyService.addHistory(new History(2L, 15));
        History history3 = historyService.addHistory(new History(3L, 50));

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

        History history = historyService.addHistory(new History(1L, 5));
        History history2 = historyService.addHistory(new History(2L, 15));

        Assertions.assertEquals(1L, history.getProductId());
        Assertions.assertEquals(5, history.getQuantity());

        Assertions.assertEquals(2L, history2.getProductId());
        Assertions.assertEquals(15, history2.getQuantity());
    }

    @Test
    @DisplayName("Add a history to the repository")
    void addHistory() {

        Assertions.assertTrue(historyService.getHistory().isEmpty(), "The repository was not empty");

        History history = historyService.addHistory(new History(1L, 5));

        Assertions.assertFalse(historyService.getHistory().isEmpty(), "The history was not added to the repository");
        Assertions.assertEquals(1L, historyService.getHistory(history.getId()).getProductId());
        Assertions.assertEquals(5, historyService.getHistory(history.getId()).getQuantity());
    }

    @Test
    @DisplayName("Update an existing history with other data")
    void updateHistory() {

        Product product = productService.addProduct(new Product("Chocolate", 1.00F));
        Product product2 = productService.addProduct(new Product("Water", 2.00F));

        History history = historyService.addHistory(new History(product.getId(), 5));
        History history2 = new History(product2.getId(), 15);

        Long id1 = history.getId();

        Assertions.assertEquals(1L, historyService.getHistory(id1).getProductId());
        Assertions.assertEquals(5, historyService.getHistory(id1).getQuantity());
        Assertions.assertEquals(history.getDate(), historyService.getHistory(id1).getDate());

        History history3 = historyService.updateHistory(id1, history2);

        Assertions.assertEquals(2L, historyService.getHistory(id1).getProductId());
        Assertions.assertEquals(15, historyService.getHistory(id1).getQuantity());
        Assertions.assertEquals(history2.getDate(), historyService.getHistory(id1).getDate());

        productService.deleteProduct(); //Cleanup
    }

    @Test
    @DisplayName("Delete a history")
    void deleteSingleHistory() {
        History history = historyService.addHistory(new History(1L, 5));

        Assertions.assertFalse(historyService.getHistory().isEmpty(), "The product was not added to the repository");
        historyService.deleteHistory(history.getId());
        Assertions.assertTrue(historyService.getHistory().isEmpty(), "The product was not deleted or there were other products in the repository");
    }

    @Test
    @DisplayName("Get the product data out from a history")
    void getHistoryProduct() {
        Product product = productService.addProduct(new Product("Chocolate", 1.00F));
        History history = historyService.addHistory(new History(product.getId(), 5));

        Product retrieved = historyService.getHistoryProduct(history.getId());

        Assertions.assertEquals(product.getName(), retrieved.getName());
        Assertions.assertEquals(product.getPrice(), retrieved.getPrice());

        productService.deleteProduct(); //Cleanup
    }

    @AfterEach
    void tearDown() {
        System.out.println("--- Test finished ---");
    }
}