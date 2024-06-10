package org.commerce.service;

import lombok.extern.slf4j.Slf4j;
import org.commerce.entity.Product;
import org.commerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductService {
    @Autowired
    private ProductRepository productRepository;


    private boolean isValidProduct(Product product) {
        boolean isValidName = product.getName() != null && product.getName().length() >= 3;
        boolean isValidDescription = product.getDesc() != null && product.getDesc().length() >= 3;
        boolean isValidPrice = product.getPrice() > 0;
        boolean isValidImage = product.getImg() != null && product.getImg().length() > 0;

        return isValidName && isValidDescription && isValidPrice && isValidImage;
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }
}
