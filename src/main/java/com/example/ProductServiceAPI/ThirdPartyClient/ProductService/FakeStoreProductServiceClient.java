package com.example.ProductServiceAPI.ThirdPartyClient.ProductService;

import com.example.ProductServiceAPI.DTOs.GenericProductDTO;
import com.example.ProductServiceAPI.Exceptions.InvalidInputException;
import com.example.ProductServiceAPI.Exceptions.NotFoundException;
import com.example.ProductServiceAPI.Service.FakeStoreProductService;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.nio.file.FileAlreadyExistsException;
import java.util.Arrays;
import java.util.List;

@Component
public class FakeStoreProductServiceClient {

    private RestTemplateBuilder restTemplateBuilder;

    String productRequestBaseURL = "https://fakestoreapi.com/products";

    String productByIdURL = productRequestBaseURL + "/{id}";

    public FakeStoreProductServiceClient(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public List<FakeStoreClientDTO> getAllProducts() throws NotFoundException {

        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<FakeStoreClientDTO[]> response = restTemplate.getForEntity(productRequestBaseURL,FakeStoreClientDTO[].class);

        return Arrays.asList(response.getBody());
    }

    public FakeStoreClientDTO getProductById(Long id) throws NotFoundException{

        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<FakeStoreClientDTO> response = restTemplate.getForEntity(productByIdURL,FakeStoreClientDTO.class,id);

        return response.getBody();
    }

    public FakeStoreClientDTO createProduct(GenericProductDTO genericProductDTO) throws InvalidInputException {

        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<FakeStoreClientDTO> response = restTemplate.postForEntity(productRequestBaseURL,genericProductDTO,FakeStoreClientDTO.class);

        return response.getBody();
    }

    public FakeStoreClientDTO deleteProductById(Long id) throws NotFoundException{

        RestTemplate restTemplate = restTemplateBuilder.build();

        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreClientDTO.class);
        ResponseExtractor<ResponseEntity<FakeStoreClientDTO>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreClientDTO.class);

        ResponseEntity<FakeStoreClientDTO> response = restTemplate.execute(productByIdURL, HttpMethod.DELETE, requestCallback, responseExtractor, id);

        return response.getBody();
    }

    public FakeStoreClientDTO updateProductById(Long id) throws InvalidInputException{
        RestTemplate restTemplate = restTemplateBuilder.build();
        restTemplate.put(productByIdURL,FakeStoreClientDTO.class,id);

        ResponseEntity<FakeStoreClientDTO> response = restTemplate.getForEntity(productByIdURL,FakeStoreClientDTO.class,id);

        return response.getBody();

    }
}
