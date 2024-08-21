package org.commerce.repository;

import org.commerce.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CartRepository extends JpaRepository<Cart, String> {
    List<Cart> findBySizeId_ProductId_Id(String productId);
}
