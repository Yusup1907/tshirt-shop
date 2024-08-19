package org.commerce.repository;

import org.commerce.entity.Order;
import org.commerce.model.order.OrderProductSizeDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
//    @Query("SELECT new org.commerce.model.order.OrderProductSizeDTO(p.name, p.price, p.img, s.size, o.orderDate FROM User u JOIN Order o ON o.userId = u.id JOIN Size s ON s.sizeId = o.id JOIN Product p ON p.ProductId = s.id WHERE u.id = :id")
//    List<OrderProductSizeDTO> findProductSizesByProductId(@Param("id") String id);

    @Query(value = "SELECT o FROM Order o WHERE o.userId = :userId")
    List<Order> findOrdersByUserId(@Param("userId") String userId);

}