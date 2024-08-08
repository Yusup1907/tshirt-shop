package org.commerce.service;

import lombok.extern.slf4j.Slf4j;
import org.commerce.entity.Product;
import org.commerce.entity.User;
import org.commerce.exception.GeneralException;
import org.commerce.model.product.ProductDTO;
import org.commerce.repository.ProductRepository;
import org.commerce.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class ProductService {
    @Autowired
    private ProductRepository productRepository;




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
            product.setDesc(request.getDescription());
            product.setPrice(request.getPrice());
            product.setImg(request.getImage());

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
            response.setName(request.getName());
            response.setDescription(request.getDescription());
            response.setPrice(request.getPrice());
            response.setImage(request.getImage());
            
            return response;
            
        } catch (GeneralException e) {
            log.error("rc : {} message system : {}  rm : {}", e.getStatusCode(), e.getMessage(), e.getGeneralMessage());
            throw new GeneralException(e.getStatusCode(), e.getMessage(), e.getGeneralMessage());

        } catch (Exception e) {
            log.error("Error creating user", e);
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
            log.error("rc : {} message system : {}  rm : {}", e.getStatusCode(), e.getMessage(), e.getGeneralMessage());
            throw new GeneralException(e.getStatusCode(), e.getMessage(), e.getGeneralMessage());
        } catch (Exception e) {
            log.error("Error during login", e.getMessage());
            throw new GeneralException("500", null, "Internal server error");
        }

    }
}
