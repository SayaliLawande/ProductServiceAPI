package com.example.ProductServiceAPI.Service;

import com.example.ProductServiceAPI.DTOs.GenericProductDTO;
import com.example.ProductServiceAPI.Exceptions.InvalidInputException;
import com.example.ProductServiceAPI.Exceptions.NotFoundException;
import com.example.ProductServiceAPI.ThirdPartyClient.ProductService.SelfProductClientDTO;
import com.example.ProductServiceAPI.ThirdPartyClient.ProductService.SelfProductServiceClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("selfProductService")
public class SelfProductService implements ProductService{

    private SelfProductServiceClient selfProductServiceClient;

    public SelfProductService(SelfProductServiceClient selfProductServiceClient){
        this.selfProductServiceClient = selfProductServiceClient;
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
    }

    @Override
    public GenericProductDTO getProductById(Long id, Long userIdTryingToAccess) throws NotFoundException {
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
