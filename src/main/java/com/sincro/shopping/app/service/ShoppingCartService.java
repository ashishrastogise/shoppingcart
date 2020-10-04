package com.sincro.shopping.app.service;

import java.math.BigDecimal;
import java.util.List;

import com.sincro.shopping.app.domain.Customer;
import com.sincro.shopping.app.domain.Discount;
import com.sincro.shopping.app.domain.DiscountCalculator;
import com.sincro.shopping.app.domain.Product;
import com.sincro.shopping.app.domain.ShoppingCart;
import com.sincro.shopping.app.domain.ShoppingCartItem;
import com.sincro.shopping.app.domain.ShoppingCartRepository;
import com.sincro.shopping.app.exception.NotEnoughItemsInStockException;
import com.sincro.shopping.app.service.ProductService;

public class ShoppingCartService {

	private ProductService productService;
	private ShoppingCartRepository shoppingCartRepository;
	private DiscountCalculator discountCalculator;

	public ShoppingCartService (ProductService productService,
	                             ShoppingCartRepository shoppingCartRepository,
	                             DiscountCalculator discountCalculator) {
        this.productService = productService;
        this.shoppingCartRepository = shoppingCartRepository;
		this.discountCalculator = discountCalculator;
	}

    public void addItem(Customer customer, Product product, int quantity) {
    	abortIfNotEnoughItemsInStock(product, quantity);
	    addProductToCart(product, quantity, cartFor(customer));
    }

	public ShoppingCart cartFor(Customer customer) {
		return shoppingCartRepository.cartFor(customer);
	}

	public List<Discount> cartDiscount(Customer customer) {
		ShoppingCart shoppingCart = shoppingCartRepository.cartFor(customer);
		return discountCalculator.discountFor(shoppingCart, customer.getCustomerType());
	}
	
	public BigDecimal applyDiscount(Customer customer, List<Discount> applicableDiscounts) {
		ShoppingCart shoppingCart = shoppingCartRepository.cartFor(customer);
		return discountCalculator.applyDiscounts(shoppingCart, applicableDiscounts);
	}
	
	

	private void addProductToCart(Product prod, int quantity, ShoppingCart cart) {
		Product product = productService.productFor(prod.getProductID());
		cart.add(new ShoppingCartItem(product, quantity));
		shoppingCartRepository.save(cart);
	}

	private void abortIfNotEnoughItemsInStock(Product product, int quantity) {
		if (!productService.hasEnoughItemsInStock(product.getProductID(), quantity)) {
			throw new NotEnoughItemsInStockException();
		}
	}
}