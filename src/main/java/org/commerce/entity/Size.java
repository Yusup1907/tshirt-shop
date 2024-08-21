package org.commerce.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tshirt_size")
public class Size {

    @Id
    @Column(name = "id_size", nullable = false, columnDefinition = "VARCHAR(100)", length = 100)
    private String id;
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false, columnDefinition = "VARCHAR(100)")
    private Product productId;
    @Column(name = "size", nullable = false, columnDefinition = "VARCHAR(3)", length = 3)
    private String size;
    @Column(name = "stock", nullable = false, columnDefinition = "INTEGER")
    private int stock;

    @OneToMany(mappedBy = "sizeId")
    private List<Cart> cart;

}