package com.mycompany.petstore.product;

import com.mycompany.petstore.product.dao.ProductRepository;
import com.mycompany.petstore.product.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductService {

    @Autowired
    ProductRepository prodRepo;

    @RequestMapping("/product/{id}")
    Optional<Product> getProduct(@PathVariable("id") int id) {
        return prodRepo.findById(id);
    }

    @RequestMapping("/products")
    List<Product> getProductsForCategory(@RequestParam("id") int id) {
        return prodRepo.findByCatId(id);
    }
}
