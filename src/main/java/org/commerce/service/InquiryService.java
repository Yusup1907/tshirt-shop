package org.commerce.service;

import lombok.extern.slf4j.Slf4j;
import org.commerce.entity.Order;
import org.commerce.entity.Size;
import org.commerce.exception.GeneralException;
import org.commerce.exception.ProductException;
import org.commerce.model.inquiry.InquiryRQDTO;
import org.commerce.model.inquiry.InquiryRSDTO;
import org.commerce.model.product.ProductSizeDBDTO;
import org.commerce.repository.OrderRepository;
import org.commerce.repository.ProductRepository;
import org.commerce.repository.SizeRepository;
import org.commerce.util.StringUtil;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class InquiryService {

    private final ProductRepository productRepository;
    private final SizeRepository sizeRepository;
    private final OrderRepository orderRepository;

    public InquiryService(ProductRepository productRepository, SizeRepository sizeRepository, OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.sizeRepository = sizeRepository;
        this.orderRepository = orderRepository;
    }

    private List<ProductSizeDBDTO> findProductAndSizeById(String productId) {
        try {
            return productRepository.findProductAndSizeById(productId);
        } catch (Exception e) {
            log.warn("error : {}", e.getMessage());
        } finally {
            log.info("executed getProductAndSizeById");
        }
        return Collections.emptyList();
    }

    private int updateStockSizeByIdAndSize(InquiryRQDTO req) {
        try {
            Size findSize = sizeRepository.findByIdProductAndBySize(req.getSizeId(), req.getSize()).orElse(null);
            if (findSize != null) {
                int newStock = findSize.getStock() - req.getQuantity();
                int result = sizeRepository.updateStockSizeByIdAndSize(req.getSizeId(), req.getSize(), newStock);
                if (result <= 0) {
                    return 0;
                }
                return result;
            }
            return 0;
        } catch (Exception e) {
            log.warn("error : {}", e.getMessage());
        } finally {
            log.info("executed updateStockSizeByIdAndSize");
        }
        return 0;
    }

    private Order addOrder(Order order) {
        try {
            return orderRepository.save(order);
        } catch (Exception e) {
            log.warn("error : {}", e.getMessage());
        } finally {
            log.info("executed addOrder");
        }
        return null;
    }

    public Object inquiryGetProduct(String productId) throws ProductException {
        List<ProductSizeDBDTO> productAndSizeById = findProductAndSizeById(productId);
        if (productAndSizeById.size() <= 0) {
            log.warn("product not found");
            throw new ProductException("01", null, "Product Not Found");
        }
        return productAndSizeById;
    }

//    public InquiryRSDTO inquiry(InquiryRQDTO req) throws ProductException {
//        String idOrder = "OID" + StringUtil.setUUID();
//        Timestamp orderDate = StringUtil.getAsTimestamp();
//        Order order = new Order();
//        order.setId(idOrder);
//        order.setUserId(req.getUserId());
//        order.setSizeId(req.getSizeId());
//        order.setOrderDate(orderDate);
//        order.setQuantity(req.getQuantity());
//        order.setAmount(req.getAmount());
//        Order add = addOrder(order);
//        if (add == null) {
//            log.warn("failed add order");
//            throw new ProductException("01", null, "Database Error");
//        }
//        int update = updateStockSizeByIdAndSize(req);
//        if (update <= 0) {
//            log.warn("failed updated size");
//            throw new ProductException("01", null, "Database Error");
//        }
//        return new InquiryRSDTO(idOrder, orderDate, order.getAmount());
//    }
}
