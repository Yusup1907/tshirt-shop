package org.commerce.model.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private int statusCode;
    private String message;
    private String name;
    private String description;
    private int price;
    private String image;
    
}
