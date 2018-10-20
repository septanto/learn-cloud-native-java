package com.mycompany.petstore.product;

import com.mycompany.petstore.product.entity.Product;
import com.mycompany.petstore.product.entity.ProductUpdMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.stereotype.Component;

@Component
public class ProductUpdListener {

    @Autowired
    ProductService prodService;

    @JmsListener(destination = "${jms.ProductTopic}", subscription = "productSearchListener")
    public void receiveMessage(ProductUpdMsg msg) {
        Product product = msg.getProduct();
        boolean isDelete = msg.isDelete();
        if (isDelete) {
            prodService.deleteProduct(product);
            System.out.println("deleted " + product.getId());
        } else {
            prodService.insertUpdateProduct(product);
            System.out.println("upserted " + product.getId());
        }
    }

    @Bean
    public MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.BYTES);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }
}
