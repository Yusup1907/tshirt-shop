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
@Table(name = "tshirt_payment")
public class Payment {

    @Id
    @Column(name = "id_payment", nullable = false, columnDefinition = "VARCHAR(100)", length = 100)
    private String id;
    @Column(name = "user_id", nullable = false, columnDefinition = "VARCHAR(100)", length = 100)
    private String userId;
    @Column(name = "order_id", nullable = false, columnDefinition = "VARCHAR(100)", length = 100)
    private String orderId;
    @Column(name = "payment_transaction_id", nullable = false, columnDefinition = "VARCHAR(255)")
    private String paymentTransactionId;

}