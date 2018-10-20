package com.mycompany.petstore.product;

import com.mycompany.petstore.product.exception.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    void handleBadRequest(BadRequestException bre, HttpServletResponse response) throws IOException {

        int respCode = (bre.errCode == BadRequestException.ID_NOT_FOUND) ? HttpStatus.NOT_FOUND.value() : HttpStatus.BAD_REQUEST.value();

        response.sendError(respCode, bre.errCode + ":" + bre.getMessage());
    }
}
