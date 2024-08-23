package org.commerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "tshirt_cart_detail")
public class CartDetail {
    @Id
    @Column(name = "id_cart_detail", nullable = false, columnDefinition = "VARCHAR(100)", length = 100)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cart_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Cart cartId;

    @Column(name = "quantity", nullable = false, columnDefinition = "INTEGER")
    private int quantity;

    @Column(name = "amount", nullable = false, columnDefinition = "INTEGER")
    private int amount;


}
