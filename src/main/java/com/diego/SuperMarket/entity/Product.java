package com.diego.SuperMarket.entity;

import lombok.*;
import javax.persistence.*;

@Data
@Entity
@Table(name="PRODUCT")
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String name;
    private Float price;

    public Product(String name, Float price) {
        this.name = name;
        this.price = price;
    }
}
