package com.sincro.shopping.app.domain;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCartRepository {

	private LocalDate date;
	private Map<Integer, ShoppingCart> carts = new HashMap<>();

	public ShoppingCart cartFor(Customer customer) {
		return carts.getOrDefault(customer.getId(), createCart(customer));
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public void save(ShoppingCart cart) {
		carts.put(cart.getCustomer().getId(), cart);
	}

	private ShoppingCart createCart(Customer customer) {
		return new ShoppingCart(customer, LocalDate.now());
	}

	public void clearCart(Customer customer) {
		carts.remove(customer.getId());
	}
	
	public ShoppingCartRepository(LocalDate date) {
		super();
		this.date = date;
	}
}