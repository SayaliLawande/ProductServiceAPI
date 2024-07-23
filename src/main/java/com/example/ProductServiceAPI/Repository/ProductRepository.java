package com.example.ProductServiceAPI.Repository;

import com.example.ProductServiceAPI.Models.Category;
import com.example.ProductServiceAPI.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    Product findByTitleEquals(String title);

    /*@Query(value = CustomQueries.FIND_ALL_BY_TITLE, nativeQuery = true)
    List<Product> findAllByTitle(String title);

    Product findByTitleEqualsAndPrice_Price(String title, double price);

    List<Product> findAllByPrice_Currency(String currency);

    int countAllByPrice_Currency(String currency);*/

}
