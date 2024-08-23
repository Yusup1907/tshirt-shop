package org.commerce.model.cartDetail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.commerce.entity.Cart;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDetailRDTO {
    private int statusCode;
    private String message;
    private String cartId;
    private int quantity;
    private int amount;
}
