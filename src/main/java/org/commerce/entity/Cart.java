package org.commerce.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tshirt_cart")
public class Cart {
    @Id
    @Column(name = "id_cart", nullable = false, columnDefinition = "VARCHAR(100)", length = 100)
    private String id;
    @ManyToOne
    @JoinColumn(name = "size_id", nullable = false)
    private Size sizeId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;

}
