package org.commerce.model;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeneralResponse {
    private String responseCode ;
    private String responseMessage;
}
