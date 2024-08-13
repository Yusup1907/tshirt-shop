package org.commerce.model.size;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SizeRDTO {
    private int statusCode;
    private String message;
    private String size;
    private int stock;
}
