package com.diego.SuperMarket.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.Instant;

@Data
@Entity
@Table(name="HISTORY")
public class History {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private Instant date; // Moment of purchase
    private Long productId; // The ID of the product that was bought
    private Integer quantity; // Amount of product bought
    private Float total; // Total cost for this purchase
}
