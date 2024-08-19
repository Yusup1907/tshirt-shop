package org.commerce.controller;

import org.commerce.entity.Product;
import org.commerce.exception.GeneralException;
import org.commerce.model.product.ProductDTO;
import org.commerce.model.product.ProductSizeDBDTO;
import org.commerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/v1")
public class ProductController {
    @Autowired
    private ProductService productService;


    @PostMapping("/new-product")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO product) throws GeneralException {
        ProductDTO products = productService.createNewProduct(product);
        log.info("User created:  " + products);
        return new ResponseEntity(products, HttpStatus.CREATED);

    }

    @GetMapping("/product")
    public ResponseEntity<Object> getAllProduct() {
        Object product = productService.getAllProducts();
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductDetail(@PathVariable String id) throws GeneralException {
        Product product = productService.getProductById(id);
        log.info("id"+ product.getId());
        if (product.getId() != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
    }

    @PutMapping("/product-update/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@RequestBody ProductDTO product, @PathVariable String id) throws GeneralException {
        ProductDTO products = productService.updateProduct(product, id);
        log.info("User created:  " + products);
        return new ResponseEntity(products, HttpStatus.CREATED);

    }





    
}
