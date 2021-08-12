package com.diego.SuperMarket.entity;

import lombok.*;
import javax.persistence.*;

@Data
@Entity
@Table(name="PRODUCT")
public class Product {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String name;
    private Float price;
}
