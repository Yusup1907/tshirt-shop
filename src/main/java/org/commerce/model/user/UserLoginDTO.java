package org.commerce.model.user;


import lombok.Data;
import org.commerce.entity.User;

@Data
public class UserLoginDTO {
    private String email;
    private String password;
    private String token;
    private String refreshToken;

}
