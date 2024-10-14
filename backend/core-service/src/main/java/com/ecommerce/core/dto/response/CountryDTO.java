package com.ecommerce.core.dto.response;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CountryDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 4773536213554180880L;

    private UUID id;

    private String code;

    private String name;
}
