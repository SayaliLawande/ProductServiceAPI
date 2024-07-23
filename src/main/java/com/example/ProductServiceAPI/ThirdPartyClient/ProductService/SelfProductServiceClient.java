package com.example.ProductServiceAPI.ThirdPartyClient.ProductService;

import com.example.ProductServiceAPI.DTOs.GenericProductDTO;
import com.example.ProductServiceAPI.Service.SelfProductService;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class SelfProductServiceClient {

    RestTemplateBuilder restTemplateBuilder;

    String productRequestBaseURL = "https://fakestoreapi.com/products";

    String productByIdURL = productRequestBaseURL + "/{id}";

    public SelfProductServiceClient(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public List<SelfProductClientDTO> getAllProducts(){

        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<SelfProductClientDTO[]> response = restTemplate.getForEntity(productRequestBaseURL,SelfProductClientDTO[].class);

        return Arrays.asList(response.getBody());
    }

    public SelfProductClientDTO getProductById(Long id){

        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<SelfProductClientDTO> response = restTemplate.getForEntity(productByIdURL,SelfProductClientDTO.class,id);

        return response.getBody();
    }

    public SelfProductClientDTO createProduct(GenericProductDTO genericProductDTO){

        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<SelfProductClientDTO> response = restTemplate.postForEntity(productRequestBaseURL,genericProductDTO,SelfProductClientDTO.class);

        return response.getBody();
    }

    public SelfProductClientDTO deleteProductById(Long id) {

        RestTemplate restTemplate = restTemplateBuilder.build();

        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(SelfProductClientDTO.class);
        ResponseExtractor<ResponseEntity<SelfProductClientDTO>> responseExtractor = restTemplate.responseEntityExtractor(SelfProductClientDTO.class);

        ResponseEntity<SelfProductClientDTO> response = restTemplate.execute(productByIdURL, HttpMethod.DELETE, requestCallback, responseExtractor, id);

        return response.getBody();
    }

}
