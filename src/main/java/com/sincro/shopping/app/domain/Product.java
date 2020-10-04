package com.sincro.shopping.app.domain;

import java.math.BigDecimal;

public class Product {

	private final int productID;
	private ProductType productType;
	private final BigDecimal unitPrice;
	private final int quantity;

	public Product(int productID, ProductType productType, BigDecimal unitPrice, int quantity) {
		super();
		this.productID = productID;
		this.productType = productType;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
	}

	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	public int getProductID() {
		return productID;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + productID;
		result = prime * result + ((productType == null) ? 0 : productType.hashCode());
		result = prime * result + quantity;
		result = prime * result + ((unitPrice == null) ? 0 : unitPrice.hashCode());
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
		if (productID != other.productID)
			return false;
		if (productType != other.productType)
			return false;
		if (quantity != other.quantity)
			return false;
		if (unitPrice == null) {
			if (other.unitPrice != null)
				return false;
		} else if (!unitPrice.equals(other.unitPrice))
			return false;
		return true;
	}

}
