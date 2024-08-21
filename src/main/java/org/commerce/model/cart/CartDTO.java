package org.commerce.model.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {
    private String idCart;
    private String sizeId;
    private String userId;
}
