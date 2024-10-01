package com.me.ecommerce.dto.response;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -7559629798078881826L;
    private UUID id;
    private String sku;
    private String name;
    private BigDecimal unitPrice;
    private int unitsInStock;
    private int unitsSold;
    private String imageUrl;
    private String description;
    private UUID categoryId;
}
