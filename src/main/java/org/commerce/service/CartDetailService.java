package org.commerce.service;

import lombok.extern.slf4j.Slf4j;
import org.commerce.entity.Cart;
import org.commerce.entity.CartDetail;
import org.commerce.entity.Product;
import org.commerce.entity.Size;
import org.commerce.exception.GeneralException;
import org.commerce.model.cartDetail.CartDetailDTO;
import org.commerce.model.cartDetail.CartDetailRDTO;
import org.commerce.repository.CartDetailRepository;
import org.commerce.repository.CartRepository;
import org.commerce.repository.ProductRepository;
import org.commerce.repository.SizeRepository;
import org.commerce.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CartDetailService {
    @Autowired
    private CartDetailRepository cartDetailRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    SizeRepository sizeRepository;

    @Autowired
    ProductRepository productRepository;

    public CartDetailRDTO addCartDetail(CartDetailDTO request) throws GeneralException {
        try {
            if (request == null || request.getQuantity() <= 0) {
                throw new GeneralException("400", null, "Invalid request data");
            }
            CartDetail cartDetail = new CartDetail();
            String idCartDetail = "OID" + StringUtil.setUUID();
            cartDetail.setId(idCartDetail);
            Cart cart = cartRepository.findById(request.getCartId())
                    .orElseThrow(() -> new GeneralException("404", null, "Car is not Null"));
            cartDetail.setCartId(cart);
            cartDetail.setQuantity(request.getQuantity());

            log.info("size" + cart.getSizeId());

            Size size = sizeRepository.findById(request.getSizeId())
                    .orElseThrow(() -> new GeneralException("404", null, "Size is not Null"));

            Product product = productRepository.findById(request.getProductId())
                    .orElseThrow(() -> new GeneralException("404", null, "Product is not Null"));

            cartDetail.setAmount((int) (request.getQuantity() * product.getPrice()));

            cartDetail = cartDetailRepository.save(cartDetail);

            CartDetailRDTO response = new CartDetailRDTO();
            response.setStatusCode(201);
            response.setMessage("Add cart detail successfully!");
            response.setCartId(String.valueOf(cart));
            response.setQuantity(cartDetail.getQuantity());
            response.setAmount(cartDetail.getAmount());

            return response;

        } catch (GeneralException e) {
            log.error("{} {} {}", e.getStatusCode(), e.getMessage(), e.getGeneralMessage());
            throw new GeneralException(e.getStatusCode(), e.getMessage(), e.getGeneralMessage());

        } catch (Exception e) {
            log.error("Error Add Cart Detail", e);
            throw new GeneralException("500", null, "Internal server error");
        }
    }

}
