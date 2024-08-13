package org.commerce.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tshirt_size")
public class Size {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_size", nullable = false, columnDefinition = "VARCHAR(100)", length = 100)
    private String id;
    @Column(name = "product_id", nullable = false, columnDefinition = "VARCHAR(100)", length = 100)
    private String productId;
    @Column(name = "size", nullable = false, columnDefinition = "VARCHAR(3)", length = 3)
    private String size;
    @Column(name = "stock", nullable = false, columnDefinition = "INTEGER")
    private int stock;

}