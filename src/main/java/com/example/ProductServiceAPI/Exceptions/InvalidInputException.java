package com.example.ProductServiceAPI.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class InvalidInputException extends Exception{

    public InvalidInputException(String message){
        super(message);
    }

}
