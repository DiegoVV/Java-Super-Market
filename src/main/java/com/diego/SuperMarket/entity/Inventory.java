package com.diego.SuperMarket.entity;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name="INVENTORY")
public class Inventory {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private Product product;
    private Integer quantity;
}