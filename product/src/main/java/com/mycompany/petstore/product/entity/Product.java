package com.mycompany.petstore.product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCatId() {
        return catId;
    }
}