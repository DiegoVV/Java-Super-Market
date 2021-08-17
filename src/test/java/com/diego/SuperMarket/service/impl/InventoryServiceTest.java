package com.diego.SuperMarket.service.impl;

import com.diego.SuperMarket.entity.Inventory;
import com.diego.SuperMarket.entity.Product;
import com.diego.SuperMarket.repository.InventoryRepository;
import com.diego.SuperMarket.service.InventoryService;
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

//    @MockBean
    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private InventoryService inventoryService;

    @Test
    @DisplayName("Get all inventories")
    void getInventory() {

        Product product = new Product("Chocolate", 1.00F);
        Integer quantity = 20;
        Product product2 = new Product("Water", 2.00F);
        Integer quantity2 = 50;
        Product product3 = new Product("Garlic Bread", 3.50F);
        Integer quantity3 = 10;

        Inventory inventory = inventoryService.addInventory(new Inventory(product,quantity));
        Inventory inventory2 = inventoryService.addInventory(new Inventory(product2,quantity2));
        Inventory inventory3 = inventoryService.addInventory(new Inventory(product3,quantity3));

        List inventoryList = new ArrayList<Inventory>();
        inventoryList.add(inventory);
        inventoryList.add(inventory2);
        inventoryList.add(inventory3);

        Assertions.assertFalse(inventoryService.getInventory().isEmpty(), "No inventories were returned");
        Assertions.assertEquals(inventoryList, inventoryService.getInventory(), "Returned something other than the 3 mocked inventories");

//        Assertions.assertEquals(inventoryList,inventoryService.getInventory());
    }

    @Test
    @DisplayName("Get a specific inventory")
    void getSingleInventory() {

        Product product = new Product("Chocolate", 1.00F);
        Integer quantity = 20;
        Product product2 = new Product("Water", 2.00F);
        Integer quantity2 = 50;

        Inventory inventory = new Inventory(product,quantity);
        Inventory inventory2 = new Inventory(product2,quantity2);

        Long id1 = inventoryService.addInventory(inventory).getId();
        Long id2 = inventoryService.addInventory(inventory2).getId();

        Assertions.assertEquals(20,inventoryService.getInventory(id1).getQuantity());
        Assertions.assertEquals("Chocolate",inventoryService.getInventory(id1).getProduct().getName());

        Assertions.assertEquals(50,inventoryService.getInventory(id2).getQuantity());
        Assertions.assertEquals("Water",inventoryService.getInventory(id2).getProduct().getName());
    }

    @Test
    @DisplayName("Adding an inventory")
    void addInventory() {

        Product product = new Product("Chocolate", 1.00F);
        Integer quantity = 20;

        Inventory inventory = new Inventory(product,quantity);

        Assertions.assertEquals(20,inventoryService.addInventory(inventory).getQuantity());
        Assertions.assertEquals("Chocolate",inventoryService.addInventory(inventory).getProduct().getName());
    }

    @Test
    void updateInventory() {

        Product product = new Product("Chocolate", 1.00F);
        Integer quantity = 20;
        Product product2 = new Product("Water", 2.00F);
        Integer quantity2 = 50;

        Inventory inventory = new Inventory(product,quantity);
        Inventory inventory2 = new Inventory(product2,quantity2);

        Long id1 = inventoryService.addInventory(inventory).getId();

        Assertions.assertEquals(50,inventoryService.updateInventory(id1, inventory2).getQuantity());
        Assertions.assertEquals("Water",inventoryService.updateInventory(id1, inventory2).getProduct().getName());
    }

    @Test
    @DisplayName("Deleting an inventory")
    void deleteInventory() {

        Product product = new Product("Chocolate", 1.00F);
        Integer quantity = 20;

        Inventory inventory = inventoryService.addInventory(new Inventory(product,quantity));

        Assertions.assertFalse(inventoryService.getInventory().isEmpty(), "The inventory was not added to the repository");
        inventoryService.deleteInventory(inventory.getId());
        Assertions.assertTrue(inventoryService.getInventory().isEmpty(), "The inventory was not deleted");
    }
}