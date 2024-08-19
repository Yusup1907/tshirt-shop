package org.commerce.controller;

import lombok.extern.slf4j.Slf4j;
import org.commerce.exception.GeneralException;
import org.commerce.model.size.ProductSizeDTO;
import org.commerce.model.size.SizeDTO;
import org.commerce.model.size.SizeRDTO;
import org.commerce.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/v1")
public class SizeController {
    @Autowired
    private SizeService sizeService;

    @PostMapping("/new/size")
    public ResponseEntity<SizeRDTO> createSize(@RequestBody SizeDTO size) throws GeneralException {
        SizeRDTO sizeRDTO = sizeService.createSize(size);
        log.info("Size created:  " + sizeRDTO);
        return new ResponseEntity(sizeRDTO, HttpStatus.CREATED);
    }

    @GetMapping("product-size/{id}")
    public ResponseEntity<Object> productSizeById(@PathVariable String id) throws GeneralException {
        Object productSizeDTOS = sizeService.productSizeById(id);
        log.info("Get Product Size By Id: " + id);

        return new ResponseEntity(productSizeDTOS, HttpStatus.OK);
    }

    @GetMapping("/size/{id}")
    public ResponseEntity<Object> sizeById(@PathVariable String id) throws GeneralException {
        Object productSizeDTOS = sizeService.sizeProductById(id);
        log.info("Get Product Size By Id: " + id);

        return new ResponseEntity(productSizeDTOS, HttpStatus.OK);
    }

    @GetMapping("/size")
    public ResponseEntity<Object> getAllSize() {
        Object size = sizeService.getAllSize();
        log.info("Get All Size");

        return new ResponseEntity(size, HttpStatus.OK);
    }

    @PutMapping("/size-updated/{id}")
    public ResponseEntity<SizeRDTO> updateSize(@RequestBody SizeDTO size, @PathVariable String id) throws GeneralException {
        SizeRDTO sizeRDTO = sizeService.updateSize(size, id);
        log.info("Size created:  " + sizeRDTO);
        return new ResponseEntity(sizeRDTO, HttpStatus.OK);
    }
}
