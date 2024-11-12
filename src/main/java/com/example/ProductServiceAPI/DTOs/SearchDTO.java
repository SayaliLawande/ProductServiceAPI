package com.example.ProductServiceAPI.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SearchDTO {

    private String query;
    private int pageNumber;
    private int sizeOFPage;
    private List<SortParameter> sortByParams;
}
