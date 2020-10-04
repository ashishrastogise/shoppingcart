package com.sincro.shoppingcart.product;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.sincro.shopping.app.domain.Product;
import com.sincro.shopping.app.domain.ProductType;
import com.sincro.shopping.app.service.ProductService;

public class ProductServiceShould {

	private ProductService productService;
	
	Product p1 = new Product(1,ProductType.APPRAEL, BigDecimal.valueOf(999.0), 10);
	Product p2 = new Product(2,ProductType.GROCERRY, BigDecimal.valueOf(499.0), 20);
	Product p3 = new Product(3,ProductType.FRUIT, BigDecimal.valueOf(999.0), 15);
	Product p4 = new Product(4,ProductType.FITNESSE, BigDecimal.valueOf(999.0), 30);
	Product p5 = new Product(5,ProductType.VEGETABLE, BigDecimal.valueOf(999.0), 100);
	
	@Before
	public void initialise() {
		productService = new ProductService();
	}

	@Test public void
	inform_when_there_are_enough_items_in_stock() {
		assertThat(productService.hasEnoughItemsInStock(p1.getProductID(), 3), is(true));
		assertThat(productService.hasEnoughItemsInStock(p1.getProductID(), 15), is(false));
	}

	@Test public void
	return_a_product_type_by_its_ID() {
	    assertThat(productService.productFor(p1.getProductID()).getProductType(), is(ProductType.APPRAEL));
	}

}