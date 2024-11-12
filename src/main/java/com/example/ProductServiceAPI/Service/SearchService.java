package com.example.ProductServiceAPI.Service;

import com.example.ProductServiceAPI.DTOs.GenericProductDTO;
import com.example.ProductServiceAPI.DTOs.SortParameter;
import com.example.ProductServiceAPI.Models.Product;
import com.example.ProductServiceAPI.Repository.ProductRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService {

    private ProductRepository productRepository;

    public SearchService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    public Page<GenericProductDTO> searchProduct(String query, Pageable pageable, List<SortParameter> sortByParams){

        Sort sort;

        if(sortByParams.get(0).getSortType().equals("ASC")){
            sort = Sort.by(sortByParams.get(0).getParameterName());
        }
        else {
            sort = Sort.by(sortByParams.get(0).getParameterName()).descending();
        }

        for(int i =1;i<sortByParams.size();i++){
            if(sortByParams.get(0).getSortType().equals("ASC")){
                sort = sort.and(Sort.by(sortByParams.get(i).getParameterName()));
            }
            else {
                sort = sort.and(Sort.by(sortByParams.get(i).getParameterName()).descending());
            }

        }
        Page<Product> productPage= productRepository.findAllByTitleContaining(query, PageRequest.of(pageable.getPageNumber(),
                pageable.getPageSize(),sort));

        List<Product> products = productPage.get().collect(Collectors.toList());

        List<GenericProductDTO> genericProductDTOS = new ArrayList<>();

        for(Product product : products){
            genericProductDTOS.add(GenericProductDTO.from(product));
        }

        Page<GenericProductDTO> genericProductPage = new PageImpl<>(genericProductDTOS,
                productPage.getPageable(),productPage.getTotalPages());

        return genericProductPage;
    }
}
