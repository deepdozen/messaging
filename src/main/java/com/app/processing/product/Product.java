package com.app.processing.product;

import java.math.BigDecimal;

public class Product {
	
	private String productType;
	private BigDecimal productPrice;
	
	public Product(String productType, BigDecimal productPrice) {
		this.productType = productType;
		this.productPrice = productPrice;
	}

	public String getProductType() {
		return productType;
	}

	public BigDecimal getProductPrice() {
		return productPrice;
	}
	
	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((productType == null) ? 0 : productType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (productType == null) {
			if (other.productType != null)
				return false;
		} else if (!productType.equals(other.productType))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("%10s at %s ", getProductType(), getProductPrice());
	}
	
}
