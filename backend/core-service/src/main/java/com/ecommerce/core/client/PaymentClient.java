package com.ecommerce.core.client;

import com.ecommerce.core.dto.request.OrderIdPayPalIdDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "payment-service", url = "${payment.service.url}")
public interface PaymentClient {

    @GetMapping("/verify/{paypalOrderId}")
    ResponseEntity<Boolean> verifyPayment(@PathVariable String paypalOrderId);

    @PostMapping("/save")
    ResponseEntity<String> savePayment(@RequestBody OrderIdPayPalIdDTO orderIdPayPalIdDTO);

}
