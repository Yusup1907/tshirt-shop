package org.commerce.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "tshirt_cart")
public class Cart {
    @Id
    @Column(name = "id_cart", nullable = false, columnDefinition = "VARCHAR(100)", length = 100)
    private String id;
    @Column(name = "product_id", nullable = false, columnDefinition = "VARCHAR(100)", length = 100)
    private String productId;
    @Column(name = "user_id", nullable = false, columnDefinition = "VARCHAR(100)", length = 100)
    private String userId;
}
