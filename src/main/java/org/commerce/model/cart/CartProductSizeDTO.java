package org.commerce.model.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartProductSizeDTO {
    private String productId;
    private String name;
    private String img;
    private int price;
    private String sizeId;
    private String userId;
    private String size;
    private int quantity;
    private int amount;
}
