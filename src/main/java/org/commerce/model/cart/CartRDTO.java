package org.commerce.model.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartRDTO {
    private int statusCode;
    private String message;
    private String idCart;
    private String sizeId;
    private String userId;
}
