package com.example.ProductServiceAPI.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericProductDTO {

    //This will be used Internally in the system
    private Long id;
    private String title;
    private String desc;
    private String image;
    private String category;
    private double price;

}
