package com.sincro.shoppingcart.cart;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.sincro.shopping.app.domain.Customer;
import com.sincro.shopping.app.domain.CustomerType;
import com.sincro.shopping.app.domain.Product;
import com.sincro.shopping.app.domain.ShoppingCart;
import com.sincro.shopping.app.domain.ShoppingCartItem;

/**
 * @author Ashish
 *
 *	This is a builder class to build objects like ShoppingCart for a customer.
 */
public class ShoppingCartBuilder {

	private LocalDate creationDate = LocalDate.now();
	
	private List<ShoppingCartItem> items = new ArrayList<>();

	Customer customer = new Customer(1, "customer1", CustomerType.REGULAR, "Delhi", "919999999999");
	
	public static ShoppingCartBuilder aShoppingCart() {
		return new ShoppingCartBuilder();
	}

	public ShoppingCartBuilder createdOn(LocalDate creationDate) {
		this.creationDate = creationDate;
		return this;
	}

	public ShoppingCartBuilder ownedBy(Customer customer) {
		this.customer = customer;
		return this;
	}

	public ShoppingCartBuilder withItem(Product product, int quantity) {
		this.items.add(new ShoppingCartItem(product, quantity));
		return this;
	}

	public ShoppingCart build() {
		ShoppingCart shoppingCart = new ShoppingCart(customer, creationDate);
		items.forEach(shoppingCart::add);
		return shoppingCart;
	}
}
