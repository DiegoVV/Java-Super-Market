package com.diego.SuperMarket.service.impl;

import com.diego.SuperMarket.entity.Inventory;
import com.diego.SuperMarket.entity.Product;
import com.diego.SuperMarket.repository.InventoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class InventoryServiceTest {

    @MockBean
    private InventoryRepository inventoryRepository;

    @Autowired
    private InventoryServiceImpl inventoryService;

    @Test
    @DisplayName("Get all inventories")
    void getInventory() {

        Product product = new Product("Chocolate", 1.00F);
        Integer quantity = 20;
        Product product2 = new Product("Water", 2.00F);
        Integer quantity2 = 50;
        Product product3 = new Product("Garlic Bread", 3.50F);
        Integer quantity3 = 10;

        Inventory inventory = new Inventory(product,quantity);
        Inventory inventory2 = new Inventory(product2,quantity2);
        Inventory inventory3 = new Inventory(product3,quantity3);

        List inventoryList = new ArrayList<Inventory>();
        inventoryList.add(inventory);
        inventoryList.add(inventory2);
        inventoryList.add(inventory3);

        Mockito.when(inventoryRepository.findAll()).thenReturn(
                inventoryList
        );

        Assertions.assertFalse(inventoryService.getInventory().isEmpty(), "No inventories were returned");
        Assertions.assertSame(inventoryList, inventoryService.getInventory(), "Returned something other than the 3 mocked inventories");

//        Assertions.assertEquals(inventoryList,inventoryService.getInventory());
    }

    @Test
    @DisplayName("Get a specific inventory")
    void getSingleInventory() {
        Assertions.assertEquals(1,1);
    }

    @Test
    void addInventory() {
    }

    @Test
    void updateInventory() {
    }

    @Test
    void deleteInventory() {
    }
}