package org.commerce.model.size;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SizeDTO {
    private String productId;
    private String size;
    private int stock;
}
