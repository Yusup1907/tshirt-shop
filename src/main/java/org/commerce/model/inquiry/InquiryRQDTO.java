package org.commerce.model.inquiry;

import lombok.Data;

@Data
public class InquiryRQDTO {
    private String productId;
    private String userId;
    private String size;
    private int quantity;
    private int amount;
}
