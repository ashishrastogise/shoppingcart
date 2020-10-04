package com.sincro.shoppingcart.discount;


import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.sincro.shopping.app.domain.Customer;
import com.sincro.shopping.app.domain.CustomerType;
import com.sincro.shopping.app.domain.Discount;
import com.sincro.shopping.app.domain.DiscountCalculator;
import com.sincro.shopping.app.domain.ShoppingCart;
import com.sincro.shoppingcart.cart.ShoppingCartBuilder;

public class DiscountCalculatorShould {

	private static final Discount FIVE_PERCENT_DISCOUNT = createDiscount(5, new BigDecimal(0), new BigDecimal(0));
	private static final Discount TEN_PERCENT_DISCOUNT = createDiscount(10, new BigDecimal(5000), new BigDecimal(10000));
	private static final Discount TWENTY_PERCENT_DISCOUNT = createDiscount(20, new BigDecimal(10000), new BigDecimal(20000));
	List<Discount> discountList = new ArrayList<Discount>();

	Customer c2 = new Customer(2,"customer2", CustomerType.PREMIUM, "Mumbai", "919999999999");
	
	@Test public void
	return_the_highest_discount_for_shopping_cart() {
		discountList.add(FIVE_PERCENT_DISCOUNT);
		ShoppingCart shoppingCart = ShoppingCartBuilder.aShoppingCart().build();
		DiscountCalculator discountCalculator = new DiscountCalculator(
				asList(TEN_PERCENT_DISCOUNT, TWENTY_PERCENT_DISCOUNT, FIVE_PERCENT_DISCOUNT));

		List<Discount> discount = discountCalculator.discountFor(shoppingCart, c2.getCustomerType());

		assertThat(discount.get(0).percentage(), is(discountList.get(0).percentage()));
	}

	private static Discount createDiscount(int percentage, BigDecimal minAmount, BigDecimal maxAmount) {
		return new Discount() {
			@Override
			public float percentage() {
				return percentage;
			}

			@Override
			public boolean isApplicableTo(ShoppingCart shoppingCart, CustomerType customerType) {
				return true;
			}

			@Override
			public BigDecimal minAmount() {
				return minAmount;
			}

			@Override
			public BigDecimal maxamount() {
				return maxAmount;
			}
		};
	}

}