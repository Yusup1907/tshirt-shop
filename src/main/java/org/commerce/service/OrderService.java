package org.commerce.service;

import lombok.extern.slf4j.Slf4j;
import org.commerce.entity.Order;
import org.commerce.entity.Product;
import org.commerce.exception.GeneralException;
import org.commerce.model.order.OrderDTO;
import org.commerce.model.order.OrderProductSizeDTO;
import org.commerce.model.order.OrderRDTO;
import org.commerce.model.product.ProductDTO;
import org.commerce.model.size.ProductSizeDTO;
import org.commerce.repository.OrderRepository;
import org.commerce.repository.ProductRepository;
import org.commerce.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.util.List;

@Service
@Slf4j
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;


    private boolean isValidId(String id) {
        return StringUtils.hasText(id);
    }


    public OrderRDTO createNewOrder(OrderDTO request) throws GeneralException {
        OrderRDTO response = new OrderRDTO();

        try {
            Order order = new Order();

            String idOrder = "OID" + StringUtil.setUUID();
            order.setId(idOrder);
            Product product = productRepository.findById(request.getProductId())
                    .orElseThrow(() -> new GeneralException("404", null, "Product not found for id: " + request.getProductId()));
            order.setProductId(product.getId());
            order.setUserId(request.getUserId());
            if (!isValidId(product.getId())) {
                throw new GeneralException("400", null, "Invalid product ID");
            }
            Timestamp stamp = new Timestamp(System.currentTimeMillis());
            order.setOrderDate(stamp);

            orderRepository.save(order);
            response.setStatusCode(201);
            response.setMessage("Add Order successfully");
            response.setUserId(order.getUserId());
            response.setProductId(order.getProductId());
            response.setOrderDate(stamp);

            return response;

        } catch (GeneralException e) {
            log.error("{} {} {}", e.getStatusCode(), e.getMessage(), e.getGeneralMessage());
            throw new GeneralException(e.getStatusCode(), e.getMessage(), e.getGeneralMessage());

        } catch (Exception e) {
            log.error("Error creating Order", e);
            throw new GeneralException("500", null, "Internal server error");
        }
    }

    public Object orderProductSizeById(String orderId) throws GeneralException {
        try {
            if (!isValidId(orderId)) {
                throw new GeneralException("400", null, "Invalid Order ID");
            }

            List<Order> productSizes = orderRepository.findOrdersByUserId(orderId);

            return productSizes;

        } catch (GeneralException e) {
            log.error("{} {} {}", e.getStatusCode(), e.getMessage(), e.getGeneralMessage());
            throw new GeneralException(e.getStatusCode(), e.getMessage(), e.getGeneralMessage());

        } catch (Exception e) {
            log.error("Error order product size by id", e);
            throw new GeneralException("500", null, "Internal server error");
        }
    }

    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }
    public OrderRDTO updateOrder(OrderDTO request, String id) throws GeneralException {
        try {
            Order existingOrder = orderRepository.findById(id)
                    .orElseThrow(() -> new GeneralException("404", null, "Order Not Found!"));
            Product product = productRepository.findById(request.getProductId())
                    .orElseThrow(() -> new GeneralException("404", null, "Product Not Found!"));
            existingOrder.setProductId(product.getId());
            Timestamp stamp = new Timestamp(System.currentTimeMillis());
            existingOrder.setOrderDate(stamp);

            orderRepository.save(existingOrder);

            OrderRDTO response = new OrderRDTO();
            response.setStatusCode(200);
            response.setMessage("Updated Order successfully");
            response.setUserId(existingOrder.getUserId());
            response.setProductId(existingOrder.getProductId());
            response.setOrderDate(stamp);


            return response;


        } catch (GeneralException e) {
            log.error("{} {} {}", e.getStatusCode(), e.getMessage(), e.getGeneralMessage());
            throw new GeneralException(e.getStatusCode(), e.getMessage(), e.getGeneralMessage());

        } catch (Exception e) {
            log.error("Error Update Order", e);
            throw new GeneralException("500", null, "Internal server error");
        }
    }


}
