package com.example.ProductServiceAPI.Service;

import com.example.ProductServiceAPI.DTOs.GenericProductDTO;
import com.example.ProductServiceAPI.Exceptions.InvalidInputException;
import com.example.ProductServiceAPI.Exceptions.NotFoundException;
import com.example.ProductServiceAPI.ThirdPartyClient.ProductService.FakeStoreClientDTO;
import com.example.ProductServiceAPI.ThirdPartyClient.ProductService.FakeStoreProductServiceClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService {

    private FakeStoreProductServiceClient fakeStoreProductServiceClient;

    public FakeStoreProductService(FakeStoreProductServiceClient fakeStoreProductServiceClient){
        this.fakeStoreProductServiceClient = fakeStoreProductServiceClient;
    }


    @Override
    public List<GenericProductDTO> getAllProducts() throws NotFoundException{
        List<FakeStoreClientDTO> responseProducts = fakeStoreProductServiceClient.getAllProducts();
        List<GenericProductDTO> products = new ArrayList<>();

        for (FakeStoreClientDTO response : responseProducts) {
            GenericProductDTO genericProductDTO = convertFakeStoreDTOToGenericDTO(response);
            products.add(genericProductDTO);
        }
        return products;
    }

    @Override
    public GenericProductDTO getProductById(Long id,Long userIdTryingToAccess) throws NotFoundException{
        return convertFakeStoreDTOToGenericDTO(fakeStoreProductServiceClient.getProductById(id));
    }

    @Override
    public GenericProductDTO createProduct(GenericProductDTO genericProductDTO) throws InvalidInputException {
        return convertFakeStoreDTOToGenericDTO(fakeStoreProductServiceClient.createProduct(genericProductDTO));
    }

    @Override
    public GenericProductDTO deleteProductById(Long id) throws NotFoundException {
        return convertFakeStoreDTOToGenericDTO(fakeStoreProductServiceClient.deleteProductById(id));
    }

    @Override
    public GenericProductDTO updateProductById(Long id) throws InvalidInputException{
        return convertFakeStoreDTOToGenericDTO(fakeStoreProductServiceClient.updateProductById(id));
    }

    private GenericProductDTO convertFakeStoreDTOToGenericDTO(FakeStoreClientDTO fakeStoreClientDTO){
        GenericProductDTO genericProductDTO = new GenericProductDTO();

        genericProductDTO.setId(fakeStoreClientDTO.getId());
        genericProductDTO.setTitle(fakeStoreClientDTO.getTitle());
        genericProductDTO.setDesc(fakeStoreClientDTO.getDesc());
        genericProductDTO.setCategory(fakeStoreClientDTO.getCategory());
        genericProductDTO.setPrice(fakeStoreClientDTO.getPrice());
        genericProductDTO.setImage(fakeStoreClientDTO.getImage());

        return genericProductDTO;
    }
}
