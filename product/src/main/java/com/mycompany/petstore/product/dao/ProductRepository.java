package com.mycompany.petstore.product.dao;

import com.mycompany.petstore.product.entity.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ProductRepository extends ElasticsearchRepository<Product, String> {

    List<Product> findByCatId(int catId);
}
