package com.sincro.shopping.app.domain;

import static java.math.BigDecimal.valueOf;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
	private final Customer customer;
	private final LocalDate creationDate;
	private List<ShoppingCartItem> items = new ArrayList<>();
	private BigDecimal totalDiscount;
	private BigDecimal finalBill;

	public ShoppingCart(Customer customer, LocalDate creationDate) {
		this.customer = customer;
		this.creationDate = creationDate;
	}

	public List<ShoppingCartItem> getItems() {
		return items;
	}

	public void setItems(List<ShoppingCartItem> items) {
		this.items = items;
	}

	public Customer getCustomer() {
		return customer;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void add(ShoppingCartItem item) {
		items.add(item);
	}

	public BigDecimal total() {
		return valueOf(
				items.stream().map(item -> item.totalPrice().doubleValue()).reduce((t, acc) -> acc + t).orElse(0.0));
	}

	
	public boolean contains(ProductType productType) {
		return items.stream().anyMatch(i -> i.productType() == productType);
	}

	public int numberOf(ProductType productType) {
		return items.stream().filter(i -> i.productType() == productType).map(ShoppingCartItem::quantity)
				.reduce((quantity, acc) -> acc + quantity).orElse(0);
	}

	public BigDecimal getTotalDiscount() {
		return totalDiscount;
	}

	public void setTotalDiscount(BigDecimal totalDiscount) {
		this.totalDiscount = totalDiscount;
	}

	public BigDecimal getFinalBill() {
		this.finalBill = new BigDecimal(total().doubleValue() - totalDiscount.doubleValue());
		return finalBill;
	}

	
	@Override
	public String toString() {
		return "ShoppingCart{" + "userID=" + customer.getId() + ", creationDate=" + creationDate + ", items=" + items
				+ '}';
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
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
		ShoppingCart other = (ShoppingCart) obj;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		return true;
	}

}