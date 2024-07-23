package com.example.ProductServiceAPI;

import com.example.ProductServiceAPI.Models.Category;
import com.example.ProductServiceAPI.Models.Product;
import com.example.ProductServiceAPI.Repository.CategoryRepository;
import com.example.ProductServiceAPI.Repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class ProductServiceApiApplication implements CommandLineRunner {

	private ProductRepository productRepository;

	private CategoryRepository categoryRepository;

	public ProductServiceApiApplication(ProductRepository productRepository, CategoryRepository categoryRepository){
		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Category category = createCategoryRepoObj();
		categoryRepository.save(category);

		Product product = createProductRepoObj(category);
		productRepository.save(product);

		System.out.println("FindByTitleEquals = " +productRepository.findByTitleEquals("Iphone"));

	}

	public Product createProductRepoObj(Category category){
		Product product = new Product();
		product.setTitle("Iphone");
		product.setDesciption("best");
		product.setCategory(category);

		return product;
	}

	public Category createCategoryRepoObj(){
		Category category = new Category();
		category.setName("phones");
		return category;
	}

}
