package com.mycompany.petstore.product;

import com.mycompany.petstore.product.dao.ProductRepository;
import com.mycompany.petstore.product.entity.Product;
import com.mycompany.petstore.product.exception.BadRequestException;
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

    @Autowired
    ProductMsgProducer producer;

    @RequestMapping("/product/{id}")
    Product getProduct(@PathVariable("id") String id) {
        return prodRepo.findById(id).orElse(null);
    }

    @RequestMapping("/products")
    List<Product> getProductsForCategory(@RequestParam("id") int id) {
        return prodRepo.findByCatId(id);
    }

    @PostMapping(value = "/product")
    ResponseEntity<Product> insertProduct(@RequestBody Product product) {
        Product savedProduct = prodRepo.save(product);
        producer.sendUpdate(savedProduct, false);
        return new ResponseEntity<>(savedProduct, HttpStatus.OK);
    }

    @PutMapping(value="/product/{id}")
    Product updateProduct(@PathVariable("id") String id, @RequestBody Product product) {
        Product existingProduct = prodRepo.findById(id).orElse(null);
        if (existingProduct == null) {
            String errMsg = "Product Not found with code " + id;
            throw new BadRequestException(BadRequestException.ID_NOT_FOUND, errMsg);
        }

        existingProduct.setCatId(product.getCatId());
        existingProduct.setName(product.getName());
        Product savedProduct = prodRepo.save(existingProduct);
        producer.sendUpdate(existingProduct, false);
        return savedProduct;
    }

    @DeleteMapping(value = "/product/{id}")
    Product deleteProduct(@PathVariable("id") String id) {

        // fetch existing product then delete it
        Product existingProduct = prodRepo.findById(id).orElse(null);
        if (existingProduct == null) {
            String errMsg = "Product Not Found with code " + id;
            throw new BadRequestException(BadRequestException.ID_NOT_FOUND, errMsg);
        }
        // Return the deleted product
        prodRepo.delete(existingProduct);
        producer.sendUpdate(existingProduct, true);
        return existingProduct;
    }
}
