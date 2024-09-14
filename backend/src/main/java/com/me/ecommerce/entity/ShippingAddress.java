package com.me.ecommerce.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "shipping_address")
public class ShippingAddress {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "zip_code")
    private String zipCode;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "state_id", nullable = false)
    private State state;

    @ManyToMany(mappedBy = "shippingAddresses")
    private Set<Customer> customers = new LinkedHashSet<>();


    //    public void setCustomerShipping(Customer customer) {
//        if(customer != null) {
//            this.setCustomerShippingAddress(customer);
//            customer.setShippingAddress(this);
//        }
//    }

//    public void setCustomerBilling(Customer customer) {
//        if(customer != null) {
//            this.setCustomerBillingAddress(customer);
//            customer.setBillingAddress(this);
//        }
//    }

}