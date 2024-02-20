package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.APIResponse;
import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;

@SpringBootApplication
@RestController
@RequestMapping("/products")
public class SpringJpaPaginationSortingApplication {
	
	@Autowired 
	public ProductService productService;
	
	//get all products
	@GetMapping
	private APIResponse<List<Product>> getProducts() {
		List<Product> allProducts = productService.findAllProducts();
		return new APIResponse<>(allProducts.size(), allProducts);
	}
	
	//get product with sorting any feild by asc
	@GetMapping("/{field}")
	private APIResponse<List<Product>> getProductsWithSorting(@PathVariable String field) {
		List<Product> allProducts = productService.findProductsWithSorting(field);
		return new APIResponse<>(allProducts.size(), allProducts);
	}
	
	//get product with pagination (offset and size)
	@GetMapping("/pagination/{offset}/{pageSize}")
	private APIResponse<Page<Product>> getProductsWithPagination(@PathVariable int offset, @PathVariable int pageSize) {
		Page<Product> productsWithPagination = productService.findProductsWithPagination(offset, pageSize);
		return new APIResponse<>(productsWithPagination.getSize(), productsWithPagination);
	}
	
	//get product with pagination (offset and size) & sorting any field by desc
		@GetMapping("/PaginationAndSort/{offset}/{pageSize}/{field}")
		private APIResponse<Page<Product>> getProductsWithPaginationAndSorting(@PathVariable int offset, @PathVariable int pageSize, @PathVariable String field) {
			Page<Product> productsWithPaginationSort = productService.findProductsWithPaginationAndSorting(offset, pageSize, field);
			return new APIResponse<>(productsWithPaginationSort.getSize(), productsWithPaginationSort);
		}


	public static void main(String[] args) {
		SpringApplication.run(SpringJpaPaginationSortingApplication.class, args);
	}

}