package com.mycompany.petstore.product.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id = 1;

    @Column(nullable = false)
    private String name = "Oranges";

    @Column(nullable = false)
    private int catId = 2;

    public Product(int id) {
        this.id = id;
    }

    public Product() {
    }

}
