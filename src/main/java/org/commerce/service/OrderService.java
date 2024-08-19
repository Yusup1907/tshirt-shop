package org.commerce.service;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.commerce.entity.Order;
import org.commerce.exception.GeneralException;
import org.commerce.model.order.OrderDTO;
import org.commerce.model.order.OrderProductSizeDTO;
import org.commerce.model.order.OrderRDTO;
import org.commerce.model.product.ProductDTO;
import org.commerce.model.size.ProductSizeDTO;
import org.commerce.repository.OrderRepository;
import org.commerce.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;


    private boolean isValidId(String id) {
        return StringUtils.hasText(id);
    }


    public OrderRDTO createNewOrder(OrderDTO request) throws GeneralException {
        OrderRDTO response = new OrderRDTO();

        try {
            Order order = new Order();

            String idOrder = "OID" + StringUtil.setUUID();
            order.setId(idOrder);
            order.setSizeId(request.getSizeId());
            order.setUserId(request.getUserId());
            if (!isValidId(request.getSizeId())) {
                throw new GeneralException("400", null, "Invalid product ID");
            }
            Timestamp stamp = new Timestamp(System.currentTimeMillis());
            order.setOrderDate(stamp);

            orderRepository.save(order);
            response.setStatusCode(201);
            response.setMessage("Add Order successfully");
            response.setUserId(order.getUserId());
            response.setSizeId(order.getSizeId());
            response.setOrderDate(stamp);

            return response;

        } catch (GeneralException e) {
            log.error("{} {} {}", e.getStatusCode(), e.getMessage(), e.getGeneralMessage());
            throw new GeneralException(e.getStatusCode(), e.getMessage(), e.getGeneralMessage());

        } catch (Exception e) {
            log.error("Error creating product", e);
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
            log.error("Error creating product", e);
            throw new GeneralException("500", null, "Internal server error");
        }
    }


}
