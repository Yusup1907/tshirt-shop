package org.commerce.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "tshirt_order_detail")
public class OrderDetail {

    @Id
    @Column(name = "id_order_detail", nullable = false, columnDefinition = "VARCHAR(100)", length = 100)
    private String id;
    @Column(name = "size_id", nullable = false, columnDefinition = "VARCHAR(100)", length = 100)
    private String sizeId;
    @Column(name = "user_id", nullable = false, columnDefinition = "VARCHAR(100)", length = 100)
    private String userId;
    @Column(name = "order_date", nullable = false, columnDefinition = "TIMESTAMP")
    private Timestamp orderDate;
    @Column(name = "quantity", nullable = false, columnDefinition = "INTEGER")
    private int quantity;
    @Column(name = "amount", nullable = false, columnDefinition = "INTEGER")
    private int amount;
}
