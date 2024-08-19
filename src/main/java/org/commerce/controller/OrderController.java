package org.commerce.controller;

import lombok.extern.slf4j.Slf4j;
import org.commerce.exception.GeneralException;
import org.commerce.model.order.OrderDTO;
import org.commerce.model.order.OrderRDTO;
import org.commerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/v1")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/new-order")
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO request) throws GeneralException {
        OrderRDTO order = orderService.createNewOrder(request);
        log.info("Order created:  " + order);
        return new ResponseEntity(order, HttpStatus.CREATED);
    }

    @GetMapping("order/{id}")
    public ResponseEntity<Object> orderProductSizeById(@PathVariable String id) throws GeneralException {
        Object orders = orderService.orderProductSizeById(id);
        log.info("Get Product Size By Id: " + id);

        return new ResponseEntity(orders, HttpStatus.OK);
    }

    @GetMapping("/order")
    public ResponseEntity<Object> getAllOrder() {
        Object order = orderService.getAllOrder();
        return new ResponseEntity(order, HttpStatus.OK);
    }

    @PutMapping("/order-updated/{id}")
    public ResponseEntity<OrderDTO> updateOrder(@RequestBody OrderDTO request, @PathVariable String id) throws GeneralException {
        OrderRDTO order = orderService.updateOrder(request, id);
        log.info("Update Order successfully!");

        return new ResponseEntity(order, HttpStatus.OK);
    }
}
