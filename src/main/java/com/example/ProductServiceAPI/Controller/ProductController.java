package com.example.ProductServiceAPI.Controller;

import com.example.ProductServiceAPI.DTOs.GenericProductDTO;
import com.example.ProductServiceAPI.Exceptions.InvalidInputException;
import com.example.ProductServiceAPI.Exceptions.NotFoundException;
import com.example.ProductServiceAPI.Security.JwtObject;
import com.example.ProductServiceAPI.Security.TokenValidator;
import com.example.ProductServiceAPI.Service.ProductService;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    private TokenValidator tokenValidator;

    //Injection by using Constructor. Injecting productSErvice. And using Qualifier.
    public ProductController(@Qualifier("fakeStoreProductService") ProductService productService, TokenValidator tokenValidator){
        this.productService = productService;
        this.tokenValidator = tokenValidator;
    }

    @GetMapping("/")
    public List<GenericProductDTO> getAllProducts() throws NotFoundException {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public GenericProductDTO getProductById(/*@Nullable @RequestHeader(HttpHeaders.AUTHORIZATION) String authToken,*/ @PathVariable("id") Long id) throws NotFoundException{
        /*System.out.println("authToken = "+authToken);

        Optional<JwtObject> authTokenObjOptional;

        JwtObject authTokenObj = null;

        if(authToken!=null){
            authTokenObjOptional = tokenValidator.validateToken(authToken);

            if(authTokenObjOptional.isEmpty()){
                //body
            }

            authTokenObj = authTokenObjOptional.get();
        }

        GenericProductDTO genericProductDTO = productService.getProductById(id,authTokenObj.getUserId());
        return  productService.getProductById(id,authTokenObj.getUserId());
        //return  productService.getProductById(id,authTokenObj.getUsIerd());*/

        GenericProductDTO genericProductDTO = productService.getProductById(id);
        return  productService.getProductById(id);
    }

    @PostMapping("/")
    public GenericProductDTO createProduct(@RequestBody GenericProductDTO product) throws InvalidInputException {
        return productService.createProduct(product);
    }

    @DeleteMapping("/{id}")
    public GenericProductDTO deleteById(@PathVariable("id") Long id) throws NotFoundException{
        return productService.deleteProductById(id);
    }

    @PutMapping("/{id}")
    public GenericProductDTO updateProduct(@PathVariable("id") Long id) throws InvalidInputException{
        return productService.updateProductById(id);
    }


}
