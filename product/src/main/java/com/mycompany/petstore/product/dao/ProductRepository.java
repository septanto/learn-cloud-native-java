package com.mycompany.petstore.product.dao;

import com.mycompany.petstore.product.entity.Product;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Integer> {

    @Cacheable("productsByCategoryCache")
    List<Product> findByCatId(int catId);
}
