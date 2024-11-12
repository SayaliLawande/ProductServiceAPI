package com.example.ProductServiceAPI.Security;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

//so that springboot can automatically
@Service
public class TokenValidator {

    private RestTemplateBuilder restTemplateBuilder;

    /*public TokenValidator(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public Optional<JwtObject> validateToken(String token){
        RestTemplate restTemplate = restTemplateBuilder.build();

        return Optional.empty();
    }*/

}
