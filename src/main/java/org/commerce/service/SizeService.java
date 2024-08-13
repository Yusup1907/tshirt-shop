package org.commerce.service;


import lombok.extern.slf4j.Slf4j;
import org.commerce.entity.Product;
import org.commerce.entity.Size;
import org.commerce.exception.GeneralException;
import org.commerce.model.size.ProductSizeDTO;
import org.commerce.model.size.SizeDTO;
import org.commerce.model.size.SizeRDTO;
import org.commerce.repository.ProductRepository;
import org.commerce.repository.SizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class SizeService {
    @Autowired
    SizeRepository sizeRepository;

    @Autowired
    ProductRepository productRepository;


    private boolean isValidProductId(String productId) {
        return StringUtils.hasText(productId);
    }

    public SizeRDTO createSize(SizeDTO request, String id) throws GeneralException {
        SizeRDTO response = new SizeRDTO();
        try {
            Size size = new Size();
            Product product = productRepository.findById(id)
                    .orElseThrow(() -> new GeneralException("404", null, "Product not found for id: " + id));
            size.setProductId(id);
            size.setSize(request.getSize());
            size.setStock(request.getStock());

            sizeRepository.save(size);
            response.setStatusCode(201);
            response.setSize(request.getSize());
            response.setStock(request.getStock());

            return response;
        } catch (GeneralException e) {
            log.error("{} {} {}", e.getStatusCode(), e.getMessage(), e.getGeneralMessage());
            throw new GeneralException(e.getStatusCode(), e.getMessage(), e.getGeneralMessage());

        } catch (Exception e) {
            log.error("Error creating size and stock", e);
            throw new GeneralException("500", null, "Internal server error");
        }
    }

    public Object productSizeById(String productId) throws GeneralException {
        try {
            if (!isValidProductId(productId)) {
                throw new GeneralException("400", null, "Invalid product ID");
            }

            Optional<Product> product = productRepository.findById(productId);
            if (product.isEmpty()) {
                throw new GeneralException("404", null, "Product Not FOUND fo Produtct ID: " + productId);
            }
            List<ProductSizeDTO> productSizes = sizeRepository.findProductSizesByProductId(productId);
            if (productSizes.isEmpty()) {
                return product;
            }

            return productSizes;

        } catch (GeneralException e) {
            log.error("{} {} {}", e.getStatusCode(), e.getMessage(), e.getGeneralMessage());
            throw new GeneralException(e.getStatusCode(), e.getMessage(), e.getGeneralMessage());

        } catch (Exception e) {
            log.error("Error creating size and stock", e);
            throw new GeneralException("500", null, "Internal server error");
        }

    }


}
