package com.mycompany.petstore.product.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "product", type = "external")
public class Product {

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
