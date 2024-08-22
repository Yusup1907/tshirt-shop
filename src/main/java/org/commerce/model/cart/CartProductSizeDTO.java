package org.commerce.model.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.commerce.entity.Size;
import org.commerce.entity.User;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartProductSizeDTO {
    private List<Size> sizeId;
    private List<User> userId;
}
