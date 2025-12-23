package com.example.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.main.models.Product;
import com.example.main.services.ProductService;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping
	public List<Product> getAllProducts() {
		return productService.getAllProducts();
	}

	@GetMapping("/filter")
	public Page<Product> filterProducts(@RequestParam(required = false) String category,
			@RequestParam(defaultValue = "0") int page) {
		return productService.getProducts(page, category);
	}

	@GetMapping("/search")
	public Page<Product> search(@RequestParam String name, @RequestParam(defaultValue = "0") int page) {
		return productService.searchProducts(name, page);
	}

	@GetMapping("/view/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable Long id) {
		Product product = productService.getProductById(id);
		return product != null ? ResponseEntity.ok(product) : ResponseEntity.notFound().build();
	}

	@PostMapping("/add")
	public ResponseEntity<Product> addProduct(@RequestBody Product product) {
		return ResponseEntity.ok(productService.saveProduct(product));
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
		try {
			return ResponseEntity.ok(productService.updateProduct(id, productDetails));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
		try {
			productService.deleteProduct(id);
			return ResponseEntity.ok("Product deleted successfully");
		} catch (Exception e) {
			return ResponseEntity.status(404).body("Error: " + e.getMessage());
		}
	}
}