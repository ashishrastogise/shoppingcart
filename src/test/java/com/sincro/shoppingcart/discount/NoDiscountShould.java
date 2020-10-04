package com.sincro.shoppingcart.discount;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.sincro.shopping.app.domain.Customer;
import com.sincro.shopping.app.domain.CustomerType;
import com.sincro.shopping.app.domain.Discount;
import com.sincro.shopping.app.domain.NoDiscount;
import com.sincro.shopping.app.domain.ShoppingCart;
import com.sincro.shoppingcart.cart.ShoppingCartBuilder;

public class NoDiscountShould {

	Customer c1 = new Customer(1,"customer1", CustomerType.REGULAR, "Delhi", "919999999999");
	
	private Discount discount;

	@Before
	public void initialise() {
	    discount = new NoDiscount();
	}

	@Test public void
	return_percentage_of_zero() {
	    assertThat(discount.percentage(), is(0F));
	}

	@Test public void
	be_applicable_to_any_shopping_cart() {
		ShoppingCart shoppingCart = ShoppingCartBuilder.aShoppingCart().build();

		assertThat(discount.isApplicableTo(shoppingCart, c1.getCustomerType()), is(true));
	}

}