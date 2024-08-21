package org.commerce.controller;

import lombok.extern.slf4j.Slf4j;
import org.commerce.exception.GeneralException;
import org.commerce.model.cart.CartDTO;
import org.commerce.model.cart.CartRDTO;
import org.commerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/v1")
public class CartController {
    @Autowired
    private CartService cartService;


    @PostMapping("/add-cart")
    public ResponseEntity<CartDTO> addCart(@RequestBody CartDTO request) throws GeneralException {
        CartRDTO cartDTO = cartService.addCart(request);
        log.info("successfully add cart");
        return new ResponseEntity(cartDTO, HttpStatus.OK);
    }

//    @GetMapping("/cart")
//    public ResponseEntity<Object> getAllCart() {
//        Object cart = cartService.getAlCart();
//        return new ResponseEntity(cart, HttpStatus.OK);
//    }
}
