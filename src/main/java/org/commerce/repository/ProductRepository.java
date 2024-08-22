package org.commerce.repository;


import org.commerce.entity.Product;
import org.commerce.entity.User;
import org.commerce.model.product.ProductDTO;
import org.commerce.model.product.ProductSizeDBDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

//    @Query("SELECT new org.commerce.model.product.ProductSizeDBDTO(p.id, p.name, p.desc, p.price, p.img, s.size, s.stock) FROM Product p JOIN Size s ON s.productId = p.id WHERE p.id = :id")
//    List<ProductSizeDBDTO> findProductAndSizeById(@Param("id") String id);

    List<Product> findAll();

    @Query(value = "SELECT u FROM Product u WHERE u.name = :name")
    Optional<Product> findByName(@Param("name") String name);

    
    Optional<Product> findById(String id);
}
