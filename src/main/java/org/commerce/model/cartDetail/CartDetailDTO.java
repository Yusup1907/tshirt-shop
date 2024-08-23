package org.commerce.model.cartDetail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.commerce.entity.Cart;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDetailDTO {
    private String id;
    private String cartId;
    private String sizeId;
    private String productId;
    private int quantity;
    private int amount;
}
