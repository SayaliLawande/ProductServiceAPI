package com.example.ProductServiceAPI.Controller;

import com.example.ProductServiceAPI.DTOs.GenericProductDTO;
import com.example.ProductServiceAPI.DTOs.SearchDTO;
import com.example.ProductServiceAPI.Service.SearchService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
public class SearchController {

    private SearchService searchService;

    public SearchController(SearchService searchService){
        this.searchService = searchService;
    }
        @PostMapping
        public Page<GenericProductDTO> searchProduct(@RequestBody SearchDTO searchDTO){

            return searchService.searchProduct(searchDTO.getQuery(), PageRequest.of(searchDTO.getPageNumber(),searchDTO.getSizeOFPage()),searchDTO.getSortByParams());
        }
}
