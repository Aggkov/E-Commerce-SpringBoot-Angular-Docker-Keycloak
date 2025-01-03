package com.ecommerce.core.dto.response;

import com.ecommerce.core.dto.request.OrderDTO;
import com.ecommerce.core.dto.request.OrderInfoDTO;
import com.ecommerce.core.dto.request.OrderItemDTO;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderSuccessDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1959889964645223660L;
    private String orderTrackingNumber;
    private OrderInfoDTO orderInfo;
    private List<OrderItemDTO> orderItems;
    private UserDTO user;
}