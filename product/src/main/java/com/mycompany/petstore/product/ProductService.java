package com.mycompany.petstore.product;

import com.mycompany.petstore.product.dao.ProductRepository;
import com.mycompany.petstore.product.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    Product getProduct(@PathVariable("id") int id) {
        return prodRepo.findById(id).orElse(null);
    }

    @RequestMapping("/products")
    List<Product> getProductsForCategory(@RequestParam("id") int id) {
        return prodRepo.findByCatId(id);
    }

    @PostMapping(value = "/product")
    ResponseEntity<Product> insertProduct(@RequestBody Product product) {
        Product savedProduct = prodRepo.save(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.OK);
    }

    @PutMapping(value="/product/{id}")
    ResponseEntity<Product> updateProduct(@PathVariable("id") int id, @RequestBody Product product) {
        Product existingProduct = prodRepo.findById(id).orElseThrow(NullPointerException::new);

        existingProduct.setCatId(product.getCatId());
        existingProduct.setName(product.getName());
        Product savedProduct = prodRepo.save(existingProduct);

        return new ResponseEntity<>(savedProduct, HttpStatus.OK);
    }

    @DeleteMapping(value = "/product/{id}")
    ResponseEntity<Product> deleteProduct(@PathVariable("id") int id) {
        prodRepo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
