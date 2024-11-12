package com.example.ProductServiceAPI.Service;

import com.example.ProductServiceAPI.DTOs.GenericProductDTO;
import com.example.ProductServiceAPI.Exceptions.InvalidInputException;
import com.example.ProductServiceAPI.Exceptions.NotFoundException;
import com.example.ProductServiceAPI.Models.Product;
import com.example.ProductServiceAPI.Repository.ProductRepository;
import com.example.ProductServiceAPI.ThirdPartyClient.ProductService.SelfProductClientDTO;
import com.example.ProductServiceAPI.ThirdPartyClient.ProductService.SelfProductServiceClient;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("selfProductService")
public class SelfProductService implements ProductService{

    private SelfProductServiceClient selfProductServiceClient;
    private final ProductRepository productRepository;

    public SelfProductService(SelfProductServiceClient selfProductServiceClient,
                              ProductRepository productRepository){
        this.selfProductServiceClient = selfProductServiceClient;
        this.productRepository = productRepository;
    }

    @Override
    public List<GenericProductDTO> getAllProducts() throws NotFoundException {

        List<SelfProductClientDTO> responseProducts = selfProductServiceClient.getAllProducts();
        List<GenericProductDTO> products = new ArrayList<>();

        for(SelfProductClientDTO selfProductClientDTO : responseProducts){
            GenericProductDTO genericProductDTO = convertFakeStoreDTOToGenericDTO(selfProductClientDTO);
            products.add(genericProductDTO);
        }
        return products;

        /*
        List<Product> products = productRepository.getAll(
        Pageable.ofSize(10)
        );

        //This pagerequest will get me page 2 with 10 products. So essentially it will be 11-20 products.
        PageRequest pageRequest = PageRequest.of(2,10);

        List<Product> productsAgain = productRepository.getAll(pageRequest);


        List<GenericProductDTO> genericProductDTOS = new ArrayList<>();

        for(Product product : products){
            genericProductDTOS.add(GenericProductDTO.from(product));
        }

        return genericProductDTOS;*/
    }

    @Override
    public GenericProductDTO getProductById(Long id/*, Long userIdTryingToAccess*/) throws NotFoundException {
        return convertFakeStoreDTOToGenericDTO(selfProductServiceClient.getProductById(id));
    }

    @Override
    public GenericProductDTO createProduct(GenericProductDTO genericProductDTO) throws InvalidInputException {
        return convertFakeStoreDTOToGenericDTO(selfProductServiceClient.createProduct(genericProductDTO));
    }

    @Override
    public GenericProductDTO deleteProductById(Long id) throws NotFoundException {
        return convertFakeStoreDTOToGenericDTO(selfProductServiceClient.deleteProductById(id));
    }

    @Override
    public GenericProductDTO updateProductById(Long id) throws InvalidInputException{
        return null;
    }

    private GenericProductDTO convertFakeStoreDTOToGenericDTO(SelfProductClientDTO selfProductClientDTO){
        GenericProductDTO genericProductDTO = new GenericProductDTO();

        genericProductDTO.setId(selfProductClientDTO.getId());
        genericProductDTO.setTitle(selfProductClientDTO.getTitle());
        genericProductDTO.setDesc(selfProductClientDTO.getDesc());
        genericProductDTO.setCategory(selfProductClientDTO.getCategory());
        genericProductDTO.setPrice(selfProductClientDTO.getPrice());
        genericProductDTO.setImage(selfProductClientDTO.getImage());

        return genericProductDTO;
    }
}
