package org.commerce.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

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
    private int price;
    @Column(name = "image", nullable = false, columnDefinition = "VARCHAR(100)", length = 100)
    private String img;

}