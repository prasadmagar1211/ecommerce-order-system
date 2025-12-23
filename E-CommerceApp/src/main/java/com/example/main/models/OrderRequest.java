package com.example.main.models;

public class OrderRequest {
	private Long userId;
	private Double totalAmount;

	// --- ADD THESE NEW FIELDS ---
	private String productName;
	private String productImage;
	private String address;
	private Integer quantity;

	// --- EXISTING GETTERS AND SETTERS ---
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	// --- NEW GETTERS AND SETTERS ---
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}