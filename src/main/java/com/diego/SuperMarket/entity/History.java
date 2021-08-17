package com.diego.SuperMarket.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Data
@Entity
@Table(name="HISTORY")
@AllArgsConstructor
@NoArgsConstructor
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

    public History(Long productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }
}
