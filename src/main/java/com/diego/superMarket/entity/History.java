package com.diego.superMarket.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

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
    @Column(name = "id")
    private Long id;
    @CreatedDate
    private Instant date; // Moment of purchase
    private Long productId; // The ID of the product that was bought
    private Integer quantity; // Amount of product bought
    private Float total; // Total cost for this purchase

    public History(Long productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
        this.date = Instant.now().truncatedTo(ChronoUnit.SECONDS);
    }
}
