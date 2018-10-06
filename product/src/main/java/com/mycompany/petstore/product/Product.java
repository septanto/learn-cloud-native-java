package com.mycompany.petstore.product;

import lombok.Data;

@Data
public class Product {
    private int id = 1;
    private String name = "Oranges";
    private int catId = 2;

    public Product(int id) {
        this.id = id;
    }

    public Product() {
    }
}
