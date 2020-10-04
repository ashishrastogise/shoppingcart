package com.sincro.shoppingcart.cart;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.sincro.shopping.app.domain.Customer;
import com.sincro.shopping.app.domain.CustomerType;
import com.sincro.shopping.app.domain.Discount;
import com.sincro.shopping.app.domain.DiscountCalculator;
import com.sincro.shopping.app.domain.NoDiscount;
import com.sincro.shopping.app.domain.Product;
import com.sincro.shopping.app.domain.ProductType;
import com.sincro.shopping.app.domain.RegularCustomerDiscount;
import com.sincro.shopping.app.domain.ShoppingCart;
import com.sincro.shopping.app.domain.ShoppingCartRepository;
import com.sincro.shopping.app.exception.NotEnoughItemsInStockException;
import com.sincro.shopping.app.service.ProductService;
import com.sincro.shopping.app.service.ShoppingCartService;

@RunWith(MockitoJUnitRunner.class)
public class ShoppingCartServiceShould {

	private static final int QTY_2 = 2;
	private static final Discount NO_DISCOUNT = new NoDiscount();
	private List<Discount> discountList = new ArrayList<>();

	Customer customer1 = new Customer(1,"customer1", CustomerType.REGULAR, "Delhi", "919999999999");
	Product p1 = new Product(1,ProductType.APPRAEL, BigDecimal.valueOf(999.0), 10);
	
	private static final Discount REGULAR_CUSTOMER_DISCOUNT = new RegularCustomerDiscount(20, new BigDecimal(5000), new BigDecimal(10000));
	private List<Discount> regularCustomerDiscountList = new ArrayList<>();
	
	private ShoppingCart USER_1_SHOPPING_BASKET;

	@Mock ShoppingCartRepository shoppingCartRepository;
    @Mock ProductService productService;
	@Mock DiscountCalculator discountCalculator;

    private ShoppingCartService shoppingCartService;

	@Before
    public void initialise() {
        USER_1_SHOPPING_BASKET = ShoppingCartBuilder.aShoppingCart().ownedBy(customer1).build();
		given(shoppingCartRepository.cartFor(customer1)).willReturn(USER_1_SHOPPING_BASKET);
        given(productService.productFor(1)).willReturn(p1);

        shoppingCartService =
                new ShoppingCartService(productService, shoppingCartRepository, discountCalculator);
    }

    @Test public void
    add_new_item_to_shopping_cart() {
    	discountList.add(NO_DISCOUNT);
    	ShoppingCart shoppingCart = ShoppingCartBuilder.aShoppingCart().ownedBy(customer1).withItem(p1, QTY_2).build();
	    given(discountCalculator.discountFor(shoppingCart, customer1.getCustomerType())).willReturn(discountList);
	    given(productService.hasEnoughItemsInStock(1, QTY_2)).willReturn(true);

        shoppingCartService.addItem(customer1, p1, QTY_2);

        verify(shoppingCartRepository).save(
        		ShoppingCartBuilder.aShoppingCart().ownedBy(customer1)
		                         .withItem(p1, QTY_2)
		                         .build());
    }

    @Test public void
    return_a_shopping_cart_for_a_given_user() {
        ShoppingCart shoppingCart = shoppingCartService.cartFor(customer1);

        assertThat(shoppingCart, is(USER_1_SHOPPING_BASKET));
    }

    @Test(expected = NotEnoughItemsInStockException.class) public void
    throw_exception_when_there_are_not_enough_items_in_stock() {
        given(productService.hasEnoughItemsInStock(1, QTY_2)).willReturn(false);

	    shoppingCartService.addItem(customer1, p1, QTY_2);
    }

    @Test public void
    return_the_discount_for_a_cart() {
    	regularCustomerDiscountList.add(REGULAR_CUSTOMER_DISCOUNT);
	    ShoppingCart shoppingCart = ShoppingCartBuilder.aShoppingCart().build();
	    given(shoppingCartRepository.cartFor(customer1)).willReturn(shoppingCart);
	    given(discountCalculator.discountFor(shoppingCart, customer1.getCustomerType())).willReturn(regularCustomerDiscountList);

	    List<Discount> discount = shoppingCartService.cartDiscount(customer1);

	    assertThat(discount, is(regularCustomerDiscountList));
    }
    
}
