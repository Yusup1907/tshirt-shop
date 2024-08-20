package org.commerce.model.orderDetail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDTO {
    private String orderDetailId;
    private String name;
    private int price;
    private String img;
    private String size;
    private int stock;
    private Timestamp orderDate;
}
