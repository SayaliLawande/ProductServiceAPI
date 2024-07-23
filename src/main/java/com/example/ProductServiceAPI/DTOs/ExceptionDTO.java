package com.example.ProductServiceAPI.DTOs;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Getter
@Setter
@AllArgsConstructor
public class ExceptionDTO {

    private HttpStatus errorCode;
    private String message;

}
