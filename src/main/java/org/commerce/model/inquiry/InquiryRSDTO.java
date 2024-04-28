package org.commerce.model.inquiry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InquiryRSDTO {
    private String idOrder;
    private Timestamp orderDate;
    private int amount;
}
