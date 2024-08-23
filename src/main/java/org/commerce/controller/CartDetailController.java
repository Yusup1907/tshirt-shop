package org.commerce.controller;

import lombok.extern.slf4j.Slf4j;
import org.commerce.exception.GeneralException;
import org.commerce.model.cartDetail.CartDetailDTO;
import org.commerce.model.cartDetail.CartDetailRDTO;
import org.commerce.service.CartDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/v1")
public class CartDetailController {
    @Autowired
    private CartDetailService cartDetailService;


    @PostMapping("/add/cart-detail")
    public ResponseEntity<CartDetailRDTO> addCartDetail(@RequestBody CartDetailDTO req) throws GeneralException {
        CartDetailRDTO cartDetailRDTO = cartDetailService.addCartDetail(req);
        log.info("Size created:  " + cartDetailRDTO);
        return new ResponseEntity(cartDetailRDTO, HttpStatus.CREATED);
    }
}
