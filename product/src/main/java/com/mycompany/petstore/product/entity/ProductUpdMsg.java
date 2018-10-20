package com.mycompany.petstore.product.entity;

import lombok.Data;

@Data
public class ProductUpdMsg {
    Product product;
    boolean isDelete = false;

    public ProductUpdMsg(Product product, boolean isDelete) {
        this.product = product;
        this.isDelete = isDelete;
    }
}
