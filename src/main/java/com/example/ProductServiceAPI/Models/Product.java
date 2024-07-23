package com.example.ProductServiceAPI.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Products")
public class Product extends BaseModel{

    private String title;
    private String desciption;
    private String image;

    @ManyToOne
    @JoinColumn(name ="Category_id")
    private Category category;

    /*@OneToOne(cascade = CascadeType.PERSIST)
    private Price price;
    */
}
