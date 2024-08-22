package org.commerce.service;

import lombok.extern.slf4j.Slf4j;
import org.commerce.entity.Cart;
import org.commerce.entity.Product;
import org.commerce.entity.Size;
import org.commerce.entity.User;
import org.commerce.exception.GeneralException;
import org.commerce.model.cart.CartDTO;
import org.commerce.model.cart.CartRDTO;
import org.commerce.model.size.ProductSizeDTO;
import org.commerce.repository.CartRepository;
import org.commerce.repository.ProductRepository;
import org.commerce.repository.SizeRepository;
import org.commerce.repository.UserRepository;
import org.commerce.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private SizeRepository sizeRepository;

    @Autowired
    private UserRepository userRepository;


    public CartRDTO addCart(CartDTO request) throws GeneralException {
        try {
            Cart cart = new Cart();
            Size size = sizeRepository.findById(request.getSizeId())
                    .orElseThrow(() -> new GeneralException("404", null, "Product size is Not Null"));
            String idCart = "OID" + StringUtil.setUUID();

            cart.setId(idCart);
            cart.setSizeId(size);
            User user = userRepository.findById(request.getUserId())
                    .orElseThrow(() -> new GeneralException("404", null, "User is Not Null"));

            cart.setUserId(user);

            cartRepository.save(cart);

            CartRDTO response = new CartRDTO();
            response.setStatusCode(201);
            response.setMessage("add cart successfully!");
            response.setIdCart(cart.getId());
            response.setSizeId(String.valueOf(cart.getSizeId()));
            response.setUserId(String.valueOf(cart.getUserId()));

            return response;

        } catch (GeneralException e) {
            log.error("{} {} {}", e.getStatusCode(), e.getMessage(), e.getGeneralMessage());
            throw new GeneralException(e.getStatusCode(), e.getMessage(), e.getGeneralMessage());

        } catch (Exception e) {
            log.error("Error Add Cart", e);
            throw new GeneralException("500", null, "Internal server error");
        }
    }

    public List<Cart> getAllCart() {
       return cartRepository.findAll();

    }
}
