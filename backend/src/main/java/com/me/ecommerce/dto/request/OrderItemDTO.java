package com.me.ecommerce.dto.request;

import com.me.ecommerce.dto.response.ProductDTO;
import java.io.Serial;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -1033970086784379155L;

    private Integer quantity;

    private ProductDTO product;
}
