package org.commerce.model.size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ProductSizeDTO {
    private String productId;
    private String name;
    private String desc;
    private int price;
    private String img;
    private String size;
    private int stock;
}
