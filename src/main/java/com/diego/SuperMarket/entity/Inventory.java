package com.diego.SuperMarket.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name="INVENTORY")
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    private Product product;
    private Integer quantity;

    public Inventory(Product product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }
}