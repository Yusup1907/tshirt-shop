package org.commerce.service;

import lombok.extern.slf4j.Slf4j;
import org.commerce.entity.Product;
import org.commerce.entity.Size;
import org.commerce.exception.GeneralException;
import org.commerce.model.product.ProductDTO;
import org.commerce.model.product.ProductSizeDBDTO;
import org.commerce.repository.ProductRepository;
import org.commerce.repository.SizeRepository;
import org.commerce.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SizeRepository sizeRepository;




    private Optional<Product> isNameProductAlreadyExists(String name) {
        return productRepository.findByName(name);
    }

    private boolean isValidProduct(Product product) {
        boolean isValidName = product.getName() != null && product.getName().length() >= 3;
        boolean isValidDescription = product.getDesc() != null && product.getDesc().length() >= 3;
        boolean isValidPrice = product.getPrice() > 0;
        boolean isValidImage = product.getImg() != null && product.getImg().length() >= 3;

        return isValidName && isValidDescription && isValidPrice && isValidImage;
    }

    public ProductDTO createNewProduct(ProductDTO request) throws GeneralException {
        ProductDTO response = new ProductDTO();

        try {
            Product product = new Product();
            String idProduct = "OID" + StringUtil.setUUID();
            product.setId(idProduct);
            product.setName(request.getName());
            product.setDesc(request.getDesc());
            product.setPrice(request.getPrice());
            product.setImg(request.getImg());

            if (isNameProductAlreadyExists(request.getName()).isPresent()) {
                log.error("Name Product already exists");
                throw new GeneralException("400", null, "Name Product already exists");
            }

            if(!isValidProduct(product)) {
                log.error("error" + product);
                throw new GeneralException("400", null, "Data is required!");
            }
            
            // log.info("product" + product);
            
            productRepository.save(product);
            response.setStatusCode(201);
            response.setMessage("Add Product successfully");
            response.setName(product.getName());
            response.setDesc(product.getDesc());
            response.setPrice(product.getPrice());
            response.setImg(product.getImg());

            return response;
            
        } catch (GeneralException e) {
            log.error("{} {} {}", e.getStatusCode(), e.getMessage(), e.getGeneralMessage());
            throw new GeneralException(e.getStatusCode(), e.getMessage(), e.getGeneralMessage());

        } catch (Exception e) {
            log.error("Error creating product", e);
            throw new GeneralException("500", null, "Internal server error");
        }
        
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }


    public Product getProductById(String id) throws GeneralException {
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

    public ProductDTO updateProduct(ProductDTO request, String id) throws GeneralException {
    try {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new GeneralException("404", null, "Product Not Found!"));

        if (!existingProduct.getName().equals(request.getName()) && isNameProductAlreadyExists(request.getName()).isPresent()) {
            log.error("Name Product already exists");
            throw new GeneralException("400", null, "Name Product already exists");
        }

        existingProduct.setName(request.getName());
        existingProduct.setDesc(request.getDesc());
        existingProduct.setPrice(request.getPrice());
        existingProduct.setImg(request.getImg());

        if(!isValidProduct(existingProduct)) {
            log.error("Invalid product data: " + existingProduct);
            throw new GeneralException("400", null, "Invalid product data!");
        }

        productRepository.save(existingProduct);

        ProductDTO response = new ProductDTO();
        response.setStatusCode(201);
        response.setMessage("Product updated successfully");
        response.setName(existingProduct.getName());
        response.setDesc(existingProduct.getDesc());
        response.setPrice(existingProduct.getPrice());
        response.setImg(existingProduct.getImg());

        return response;

    } catch (GeneralException e) {
        log.error("{} {} {}", e.getStatusCode(), e.getMessage(), e.getGeneralMessage());
        throw new GeneralException(e.getStatusCode(), e.getMessage(), e.getGeneralMessage());

    } catch (Exception e) {
        log.error("Error updating product", e);
        throw new GeneralException("500", null, "Internal server error");
    }
    }


}
