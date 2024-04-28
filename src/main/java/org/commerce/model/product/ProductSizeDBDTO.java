package org.commerce.model.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductSizeDBDTO {
    private String productId;
    private String name;
    private String desc;
    private int price;
    private String img;
    private String size;
    private int stock;
}
