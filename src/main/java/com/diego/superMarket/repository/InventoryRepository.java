package com.diego.superMarket.repository;

import com.diego.superMarket.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

}