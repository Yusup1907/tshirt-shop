package org.commerce.model.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRDTO {
    private int StatusCode;
    private String Message;
    private String sizeId;
    private String userId;
    private Timestamp orderDate;
}
