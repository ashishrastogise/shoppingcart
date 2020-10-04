package com.sincro.shoppingcart.cart;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.sincro.shopping.app.domain.Customer;
import com.sincro.shopping.app.domain.CustomerType;
import com.sincro.shopping.app.domain.Product;
import com.sincro.shopping.app.domain.ProductType;
import com.sincro.shopping.app.domain.ShoppingCart;
import com.sincro.shopping.app.domain.ShoppingCartRepository;

/**
 * @author Ashish
 *	Test case for ShoppingCartRepository (whild holds the shopping cart for multipe customers.
 */
@RunWith(MockitoJUnitRunner.class)
public class ShoppingCartRepositoryShould {

	Customer customer = new Customer(1, "customer1", CustomerType.REGULAR, "Delhi", "919999999999");
	private static final LocalDate CURRENT_DATE = LocalDate.now();
	private static final int QTY_2 = 2;
	Product product = new Product(1, ProductType.APPRAEL, BigDecimal.valueOf(999.0), 10);

	private ShoppingCartRepository shoppingCartRepository;

	/**
	 * Method to initialise test data.
	 */
	@Before
	public void initialise() {
		shoppingCartRepository = new ShoppingCartRepository(LocalDate.now());
	}

	/**
	 * Test to verify 
	 */
	@Test
	public void create_and_return_a_new_shopping_cart_for_a_user_when_he_does_not_have_one() {
		ShoppingCart shoppingCart = shoppingCartRepository.cartFor(customer);

		assertThat(shoppingCart,
				Is.is(ShoppingCartBuilder.aShoppingCart().createdOn(CURRENT_DATE).ownedBy(customer).build()));
	}

	@Test
	public void save_user_cart() {
		ShoppingCart userCart = ShoppingCartBuilder.aShoppingCart().createdOn(CURRENT_DATE).ownedBy(customer)
				.withItem(product, QTY_2).build();
		shoppingCartRepository.save(userCart);

		ShoppingCart shoppingCart = shoppingCartRepository.cartFor(customer);

		assertThat(shoppingCart, is(userCart));
	}

}