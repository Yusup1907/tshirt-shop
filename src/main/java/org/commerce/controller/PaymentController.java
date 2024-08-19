package org.commerce.controller;

import org.commerce.exception.GeneralException;
import org.commerce.exception.ProductException;
import org.commerce.model.inquiry.InquiryRQDTO;
import org.commerce.model.inquiry.InquiryRSDTO;
import org.commerce.service.InquiryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1")
public class PaymentController {

    private final InquiryService inquiryService;

    public PaymentController(InquiryService inquiryService) {
        this.inquiryService = inquiryService;
    }

    @GetMapping(path = "/inquiry")
    public ResponseEntity<Object> getProductAndSizeById(@RequestParam(name = "product_id") String productId) throws ProductException {
        Object products = inquiryService.inquiryGetProduct(productId);
        return ResponseEntity.ok(products);
    }

//    @PostMapping(path = "/inquiry")
//    public ResponseEntity<Object> getProductAndSizeById(@RequestBody InquiryRQDTO inquiryRQDTO) throws ProductException {
//        InquiryRSDTO inquiry = inquiryService.inquiry(inquiryRQDTO);
//        return ResponseEntity.ok(inquiry);
//    }
}
