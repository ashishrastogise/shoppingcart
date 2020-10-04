package com.sincro.shopping.app.domain;

import java.math.BigDecimal;

public class ShoppingCartItem {
    private final int quantity;
    private final Product product;

    public ShoppingCartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public BigDecimal totalPrice() {
        return BigDecimal.valueOf(quantity * product.getUnitPrice().doubleValue());
    }

    public ProductType productType() {
        return this.product.getProductType();
    }

    public int quantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "ShoppingCartItem{" +
                "product=" + product +
                ", quantity=" + quantity +
                '}';
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + quantity;
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
		ShoppingCartItem other = (ShoppingCartItem) obj;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (quantity != other.quantity)
			return false;
		return true;
	}
    
    
}