package com.sincro.shoppingcart.acceptance;

import static java.util.Collections.singletonList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.sincro.shopping.app.domain.Customer;
import com.sincro.shopping.app.domain.CustomerType;
import com.sincro.shopping.app.domain.DiscountCalculator;
import com.sincro.shopping.app.domain.NoDiscount;
import com.sincro.shopping.app.domain.Product;
import com.sincro.shopping.app.domain.ProductType;
import com.sincro.shopping.app.domain.ShoppingCart;
import com.sincro.shopping.app.domain.ShoppingCartRepository;
import com.sincro.shopping.app.service.ProductService;
import com.sincro.shopping.app.service.ShoppingCartService;
import com.sincro.shoppingcart.cart.ShoppingCartBuilder;

/**
 * @author Ashish
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class AddItemsToShoppingCartFeature {

	Customer c1 = new Customer(1,"customer1", CustomerType.REGULAR, "Delhi", "919999999999");
	Product p1 = new Product(1,ProductType.APPRAEL, BigDecimal.valueOf(1000.0), 10);
	Product p2 = new Product(2,ProductType.GROCERRY, BigDecimal.valueOf(500.0), 20);
	Product p3 = new Product(3,ProductType.FRUIT, BigDecimal.valueOf(700.0), 15);
	Product p4 = new Product(4,ProductType.FITNESSE, BigDecimal.valueOf(200.0), 30);
	Product p5 = new Product(5,ProductType.VEGETABLE, BigDecimal.valueOf(9000.0), 100);
	
    private static final LocalDate CREATION_DATE = LocalDate.now();

    private static final int QTY_2 = 2;
    private static final int QTY_5 = 5;

    private ShoppingCartService shoppingCartService;

    /**
     * This method is used to initialise test data
     */
    @Before
    public void initialise() {
    	List<Product> productList = new ArrayList<>();
		productList.add(p1);
		productList.add(p2);
		productList.add(p3);
		productList.add(p4);
		productList.add(p5);
		ShoppingCartRepository shoppingCartRepository = new ShoppingCartRepository(CREATION_DATE);
        ProductService productService = new ProductService();
        productService.addProducts(productList);
        DiscountCalculator discountCalculator = new DiscountCalculator(singletonList(new NoDiscount()));
	    shoppingCartService = new ShoppingCartService(productService, shoppingCartRepository, discountCalculator);
    }

    /**
     * Test case to return shopping cart object added for a customer
     */
    @Test public void
    return_cart_containing_all_the_items_added_to_it() {
        shoppingCartService.addItem(c1, p1, QTY_2);
        shoppingCartService.addItem(c1, p2, QTY_5);
        shoppingCartService.addItem(c1, p4, QTY_5);

        ShoppingCart shoppingCart = shoppingCartService.cartFor(c1);

        assertThat(shoppingCart, is(ShoppingCartBuilder.aShoppingCart()
                                            .createdOn(CREATION_DATE)
                                            .ownedBy(c1)
                                            .withItem(p1, QTY_2)
                                            .withItem(p2, QTY_5)
                                            .withItem(p4, QTY_5)
                                            .build()));
        assertThat(shoppingCart.total(), is(BigDecimal.valueOf(5500.0)));
    }

}
