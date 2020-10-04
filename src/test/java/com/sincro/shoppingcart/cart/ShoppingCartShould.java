package com.sincro.shoppingcart.cart;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import org.junit.Test;

import com.sincro.shopping.app.domain.Product;
import com.sincro.shopping.app.domain.ProductType;
import com.sincro.shopping.app.domain.ShoppingCart;

public class ShoppingCartShould {

	private static final BigDecimal ZERO = BigDecimal.valueOf(0.0);
	private static final int QTY_1 = 1;
	private static final int QTY_2 = 2;
	private static final int QTY_5 = 5;

	Product p1 = new Product(1, ProductType.APPRAEL, BigDecimal.valueOf(1000.0), 10);
	Product p2 = new Product(2, ProductType.GROCERRY, BigDecimal.valueOf(500.0), 20);
	Product p3 = new Product(3, ProductType.FRUIT, BigDecimal.valueOf(200.0), 15);
	Product p4 = new Product(4, ProductType.FITNESSE, BigDecimal.valueOf(700.0), 30);
	Product p5 = new Product(5, ProductType.VEGETABLE, BigDecimal.valueOf(900.0), 100);

	@Test
	public void have_total_cost_of_zero_when_empty() {
		assertThat(ShoppingCartBuilder.aShoppingCart().build().total(), is(ZERO));
	}

	@Test
	public void return_the_total_cost_for_a_cart_containing_items() {
		ShoppingCart shoppingCart = ShoppingCartBuilder.aShoppingCart()
														.withItem(p1, QTY_2)
														.withItem(p2, QTY_5)
														.build();
		BigDecimal totalCost = shoppingCart.total();
		assertThat(totalCost, is(BigDecimal.valueOf(4500.0)));
	}

	@Test
	public void return_the_number_of_products_of_one_type() {
		ShoppingCart cart = ShoppingCartBuilder.aShoppingCart()
												 .withItem(p1, QTY_2)
												 .withItem(p5, QTY_1)
												 .build();

		assertThat(cart.numberOf(ProductType.APPRAEL), is(2));
		assertThat(cart.numberOf(ProductType.VEGETABLE), is(1));
	}

	@Test
	public void return_the_number_of_products_of_same_type_when_added_separately() {
		ShoppingCart cart = ShoppingCartBuilder.aShoppingCart().withItem(p1, QTY_2).withItem(p2, QTY_5)
				.withItem(p1, QTY_5).build();

		assertThat(cart.numberOf(ProductType.APPRAEL), is(7));
	}
}