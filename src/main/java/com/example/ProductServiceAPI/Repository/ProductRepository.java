package com.example.ProductServiceAPI.Repository;

import com.example.ProductServiceAPI.Models.Category;
import com.example.ProductServiceAPI.Models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    Product findByTitleEquals(String title);

    List<Product> findAll();

    //List<Product> getAll(Pageable pageable);

    //List<Product> findAllByPrice_Currency(String currency, Pageable pageable);

    Page<Product> findAllByTitleContaining(String query,Pageable pageable);

    /*@Query(value = CustomQueries.FIND_ALL_BY_TITLE, nativeQuery = true)
    List<Product> findAllByTitle(String title);

    Product findByTitleEqualsAndPrice_Price(String title, double price);



    int countAllByPrice_Currency(String currency);*/

}
