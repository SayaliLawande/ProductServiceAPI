package com.example.ProductServiceAPI.Controller;

import com.example.ProductServiceAPI.DTOs.GenericProductDTO;
import com.example.ProductServiceAPI.Exceptions.InvalidInputException;
import com.example.ProductServiceAPI.Exceptions.NotFoundException;
import com.example.ProductServiceAPI.Service.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    //Injection by using Constructor. Injecting productSErvice. And using Qualifier.
    public ProductController(@Qualifier("selfProductService") ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/")
    public List<GenericProductDTO> getAllProducts() throws NotFoundException {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public GenericProductDTO getProductById(@PathVariable("id") Long id) throws NotFoundException{
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
