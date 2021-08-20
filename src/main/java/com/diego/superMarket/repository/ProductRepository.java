package com.diego.superMarket.repository;

import com.diego.superMarket.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
