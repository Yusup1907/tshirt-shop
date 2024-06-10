package org.commerce.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRDTO {
    private int statusCode;
    private String message;
    private String firstName;
    private String lastName;
    private String username;
    private String phone;
    private String address;

    public void getMessage(String s) {
        message = s;
    }
}
