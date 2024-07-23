package com.example.ProductServiceAPI.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "Categories")
public class Category extends BaseModel{

    @Column
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Product> productList;

}
