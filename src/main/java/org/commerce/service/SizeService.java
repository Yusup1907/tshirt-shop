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
import org.commerce.util.StringUtil;
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

    public SizeRDTO createSize(SizeDTO request) throws GeneralException {
        SizeRDTO response = new SizeRDTO();
        try {
            Size size = new Size();
            String idSize = "OID" + StringUtil.setUUID();
            size.setId(idSize);
            Product product = productRepository.findById(request.getProductId())
                    .orElseThrow(() -> new GeneralException("404", null, "Product not found for id: " + request.getProductId()));
            size.setProductId(product);
            size.setSize(request.getSize());
            size.setStock(request.getStock());

            sizeRepository.save(size);
            response.setStatusCode(201);
            response.setMessage("Add size and stock successfully!");
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


    public Object sizeProductById(String sizeId) throws GeneralException {
        try {
            if (!isValidProductId(sizeId)) {
                throw new GeneralException("400", null, "Invalid size ID");
            }


            Optional<Size> productSizes = sizeRepository.findById(sizeId);
            if (productSizes.isEmpty()) {
                throw new GeneralException("404", null, "Size not  Found");
            }


            return productSizes;

        } catch (GeneralException e) {
            log.error("{} {} {}", e.getStatusCode(), e.getMessage(), e.getGeneralMessage());
            throw new GeneralException(e.getStatusCode(), e.getMessage(), e.getGeneralMessage());

        } catch (Exception e) {
            log.error("Error size by id", e);
            throw new GeneralException("500", null, "Internal server error");
        }

    }

    public List<Size> getAllSize() {
        return sizeRepository.findAll();
    }

    public SizeRDTO updateSize(SizeDTO request, String id) throws GeneralException {
        try {
            Size existingSize = sizeRepository.findById(id)
                    .orElseThrow(() -> new GeneralException("404", null, "Size Not Found!"));
            Product product = productRepository.findById(request.getProductId())
                    .orElseThrow(() -> new GeneralException("404", null, "Product not found for id: " + request.getProductId()));
            existingSize.setProductId(product);
            existingSize.setSize(request.getSize());
            existingSize.setStock(request.getStock());

            sizeRepository.save(existingSize);

            SizeRDTO response = new SizeRDTO();
            response.setStatusCode(200);
            response.setMessage("Updated size and stock successfully!");
            response.setSize(request.getSize());
            response.setStock(request.getStock());

            return response;


        } catch (GeneralException e) {
            log.error("{} {} {}", e.getStatusCode(), e.getMessage(), e.getGeneralMessage());
            throw new GeneralException(e.getStatusCode(), e.getMessage(), e.getGeneralMessage());

        } catch (Exception e) {
            log.error("Error updated size and stock", e);
            throw new GeneralException("500", null, "Internal server error");
        }
    }


}
