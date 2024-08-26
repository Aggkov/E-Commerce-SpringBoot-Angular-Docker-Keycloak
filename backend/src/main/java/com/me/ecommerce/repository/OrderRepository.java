package com.me.ecommerce.repository;

import com.me.ecommerce.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query("SELECT o.orderTrackingNumber FROM Order o WHERE o.orderTrackingNumber = :orderTrackingNumber")
    String findOrderTrackingNumber(@Param("orderTrackingNumber") String orderTrackingNumber);
}
