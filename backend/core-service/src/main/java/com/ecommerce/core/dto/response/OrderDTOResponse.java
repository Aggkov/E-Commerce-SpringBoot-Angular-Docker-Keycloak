package com.ecommerce.core.dto.response;

import com.ecommerce.core.dto.request.OrderDTO;
import com.ecommerce.core.dto.request.OrderItemDTO;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDTOResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 1959889964645223660L;

    private String orderTrackingNumber;

    private List<OrderItemDTO> orderItems;

    private UserDTO user;

    public OrderDTOResponse(String orderTrackingNumber,
                            OrderDTO orderDTO) {
        this.orderTrackingNumber = orderTrackingNumber;
//        orderDTO.getOrderItemList().forEach(orderItemDTO -> {
//            imageUrls.add(orderItemDTO.getProduct().getImageUrl());
//        });
        this.orderItems = orderDTO.getOrderItemList();
//        imageUrls.forEach(image -> {
//            this.orderItems.forEach(orderItemDTO -> {
//                ProductDTO productDTO = orderItemDTO.getProduct();
//                productDTO.setImageUrl(image);
//            });
//        });
        this.user = orderDTO.getUser();
    }
}