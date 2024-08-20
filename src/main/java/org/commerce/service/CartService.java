package org.commerce.service;

import lombok.extern.slf4j.Slf4j;
import org.commerce.entity.Cart;
import org.commerce.entity.Product;
import org.commerce.exception.GeneralException;
import org.commerce.model.cart.CartDTO;
import org.commerce.repository.CartRepository;
import org.commerce.repository.ProductRepository;
import org.commerce.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    private Product getProductById(String id) throws GeneralException {
        try {
            return productRepository.findById(id).orElseThrow(() -> new GeneralException("404", null, "Product Not Found!"));

        } catch (GeneralException e) {
            log.error("{} {} {}", e.getStatusCode(), e.getMessage(), e.getGeneralMessage());
            throw new GeneralException(e.getStatusCode(), e.getMessage(), e.getGeneralMessage());
        } catch (Exception e) {
            log.error("Error during login", e.getMessage());
            throw new GeneralException("500", null, "Internal server error");
        }
    }

    public CartDTO addCart(CartDTO request) throws GeneralException {
        try {
            Cart cart = new Cart();
            Product product = getProductById(request.getIdCart());
            String idCart = "OID" + StringUtil.setUUID();

            product.setId(product.getId());
            product.setName(product.getName());
            product.setImg(product.getImg());
            cart.setProductId(product.getId());
            cart.setId(idCart);
            cart.setUserId(request.getUserId());
            if (request.getUserId().isEmpty()) {
                throw new GeneralException("404", null, "User Not Found!");
            }
        } catch (GeneralException e) {
            log.error("{} {} {}", e.getStatusCode(), e.getMessage(), e.getGeneralMessage());
            throw new GeneralException(e.getStatusCode(), e.getMessage(), e.getGeneralMessage());

        } catch (Exception e) {
            log.error("Error Add Cart", e);
            throw new GeneralException("500", null, "Internal server error");
        }
    }
}
