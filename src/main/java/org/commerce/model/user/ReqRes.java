package org.commerce.model.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.commerce.entity.User;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReqRes {

    private int statusCode;
    private String message;
    private String token;
    private String refreshToken;
    private String expirationTime;

    public void getStatusCode(int i) {
        statusCode = i;
    }

}
