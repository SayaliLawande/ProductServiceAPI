package com.example.ProductServiceAPI.DTOs;

import com.example.ProductServiceAPI.Models.Product;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class GenericProductDTO implements Serializable {

    //This will be used Internally in the system
    private Long id;
    private String title;
    private String desc;
    private String image;
    private String category;
    private double price;

    public static GenericProductDTO from(Product product){
        GenericProductDTO genericProductDTO = new GenericProductDTO();
        genericProductDTO.setTitle(product.getTitle());
        genericProductDTO.setDesc(product.getDesciption());
        genericProductDTO.setImage(product.getImage());
        genericProductDTO.setCategory(product.getCategory().getName());

        return genericProductDTO;
    }
}
