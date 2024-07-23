package com.example.ProductServiceAPI.ThirdPartyClient.ProductService;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SelfProductClientDTO {

    //This will be used Externally to the system. To communicate with Fakestore
    private Long id;
    private String title;
    private String desc;
    private String image;
    private String category;
    private double price;

}
