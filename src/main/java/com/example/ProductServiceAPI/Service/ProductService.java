package com.example.ProductServiceAPI.Service;

import com.example.ProductServiceAPI.DTOs.GenericProductDTO;
import com.example.ProductServiceAPI.Exceptions.InvalidInputException;
import com.example.ProductServiceAPI.Exceptions.NotFoundException;

import java.util.List;

public interface ProductService {

    public List<GenericProductDTO> getAllProducts() throws NotFoundException;

    public GenericProductDTO getProductById(Long id/*, Long userIdTryingToAccess*/) throws NotFoundException;

    public GenericProductDTO createProduct(GenericProductDTO genericProductDTO) throws InvalidInputException;

    public GenericProductDTO deleteProductById(Long id) throws NotFoundException;

    public GenericProductDTO updateProductById(Long id) throws InvalidInputException;

}
