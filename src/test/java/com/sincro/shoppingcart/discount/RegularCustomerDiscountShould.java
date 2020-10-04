package com.sincro.shoppingcart.discount;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.sincro.shopping.app.domain.Customer;
import com.sincro.shopping.app.domain.CustomerType;
import com.sincro.shopping.app.domain.Product;
import com.sincro.shopping.app.domain.ProductType;
import com.sincro.shopping.app.domain.RegularCustomerDiscount;
import com.sincro.shopping.app.domain.ShoppingCart;
import com.sincro.shoppingcart.cart.ShoppingCartBuilder;

public class RegularCustomerDiscountShould {

	private static final int QTY_2 = 2;
	private RegularCustomerDiscount discount;
	Product p1 = new Product(1,ProductType.APPRAEL, BigDecimal.valueOf(999.0), 10);
	Customer c1 = new Customer(1,"customer1", CustomerType.REGULAR, "Delhi", "919999999999");
	Customer c2 = new Customer(2,"customer2", CustomerType.PREMIUM, "Mumbai", "919999999999");

	@Before
	public void initialise() {
		discount = new RegularCustomerDiscount(10,new BigDecimal(5000), new BigDecimal(10000));
	}

	@Test public void
	return_its_percentage() {
		assertThat(discount.percentage(), is(10F));
	}

	@Test public void
	not_be_applicable_for_carts_without_a_book() {
		ShoppingCart cart = ShoppingCartBuilder.aShoppingCart()
										.withItem(p1, QTY_2)
										.build();

	    assertThat(discount.isApplicableTo(cart, c2.getCustomerType()), is(false));
	}

	@Test public void
	not_be_applicable_for_carts_without_a_video() {
		ShoppingCart cart = ShoppingCartBuilder.aShoppingCart()
										.withItem(p1, QTY_2)
										.build();

	    assertThat(discount.isApplicableTo(cart, c1.getCustomerType()), is(false));
	}

}