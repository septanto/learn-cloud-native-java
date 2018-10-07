package com.mycompany.petstore.product.dao;

import com.mycompany.petstore.product.entity.Product;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {

    List<Product> findByCatId(int catId);
}
