package com.ecommerce.core.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "country")
public class Country {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "code", length = 2)
    private String code;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "country")
    private Set<State> states = new LinkedHashSet<>();
}