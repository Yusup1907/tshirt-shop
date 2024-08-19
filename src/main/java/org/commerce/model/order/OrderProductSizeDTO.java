package org.commerce.model.order;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.commerce.model.size.SizeDTO;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderProductSizeDTO {
    private String name;
    private int price;
    private String img;
    private String sizes;
    private Timestamp orderDate;
}
