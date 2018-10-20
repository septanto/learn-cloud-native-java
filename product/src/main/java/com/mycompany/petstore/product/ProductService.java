package com.mycompany.petstore.product;

import com.mycompany.petstore.product.dao.ProductRepository;
import com.mycompany.petstore.product.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductService {

    @Autowired
    ProductRepository prodRepo;

    @RequestMapping("/product/{id}")
    Product getProduct(@PathVariable("id") String id) {
        return prodRepo.findById(id).orElse(null);
    }

    @RequestMapping("/products")
    List<Product> getProductsForCategory(@RequestParam("id") int id) {
        return prodRepo.findByCatId(id);
    }

    @PutMapping("/product/{id}")
    public void insertUpdateProduct(@RequestBody Product product) {
        prodRepo.save(product);
    }

    @DeleteMapping("/product/{id}")
    public void deleteProduct(@RequestBody Product product) {
        prodRepo.delete(product);
    }

}
