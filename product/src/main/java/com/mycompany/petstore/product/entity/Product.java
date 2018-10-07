package com.mycompany.petstore.product.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document
public class Product implements Serializable {

    @Id
    private String id;
    private String name;
    private int catId;

    public Product(String id) {
        this.id = id;
    }

    public Product() {
    }
}
