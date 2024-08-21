package org.commerce.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tshirt_product")
public class Product {

    @Id
    @Column(name = "id_product", nullable = false, columnDefinition = "VARCHAR(100)", length = 100)
    private String id;
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(100)", length = 100)
    private String name;
    @Column(name = "description", nullable = false, columnDefinition = "VARCHAR(100)", length = 100)
    private String desc;
    @Column(name = "price", nullable = false, columnDefinition = "INTEGER")
    private double price;
    @Column(name = "image", nullable = false, columnDefinition = "VARCHAR(100)", length = 100)
    private String img;

    @OneToMany(mappedBy = "productId")
    private List<Size> size;

}