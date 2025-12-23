package com.example.main.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.main.models.Product;
import com.example.main.repositories.ProductRepository;
import java.util.List;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	// Admin Panel fetch
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	// UPDATED: Logic for storefront filtering
	public Page<Product> getProducts(int page, String category) {
		Pageable pageable = PageRequest.of(page, 8);

		// Handle "All" category as "no filter"
		if (category == null || category.isEmpty() || category.equalsIgnoreCase("All")) {
			return productRepository.findAll(pageable);
		}
		return productRepository.findByCategory(category, pageable);
	}

	public Page<Product> searchProducts(String name, int page) {
		Pageable pageable = PageRequest.of(page, 8);
		return productRepository.findByNameContainingIgnoreCase(name, pageable);
	}

	public Product getProductById(Long id) {
		return productRepository.findById(id).orElse(null);
	}

	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}

	public Product updateProduct(Long id, Product details) {
		Product existingProduct = productRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Product not found"));
		existingProduct.setName(details.getName());
		existingProduct.setPrice(details.getPrice());
		existingProduct.setImageUrl(details.getImageUrl());
		// Uncomment if you use these:
		// existingProduct.setCategory(details.getCategory());
		return productRepository.save(existingProduct);
	}

	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}
}