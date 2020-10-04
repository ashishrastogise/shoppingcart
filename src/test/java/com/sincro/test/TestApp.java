package com.sincro.test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.sincro.shopping.app.domain.Customer;
import com.sincro.shopping.app.domain.CustomerType;
import com.sincro.shopping.app.domain.Discount;
import com.sincro.shopping.app.domain.DiscountCalculator;
import com.sincro.shopping.app.domain.PremiumCustomerDiscount;
import com.sincro.shopping.app.domain.Product;
import com.sincro.shopping.app.domain.ProductType;
import com.sincro.shopping.app.domain.RegularCustomerDiscount;
import com.sincro.shopping.app.domain.ShoppingCart;
import com.sincro.shopping.app.domain.ShoppingCartRepository;
import com.sincro.shopping.app.service.ProductService;
import com.sincro.shopping.app.service.ShoppingCartService;

public class TestApp {

	private static final LocalDate CREATION_DATE = LocalDate.now();
	
	public static void main(String[] args) {
		setupTestData();
	}

	private static void setupTestData() {
		Customer c1 = new Customer(1,"customer1", CustomerType.REGULAR, "Delhi", "9953870879");
		Customer c2 = new Customer(2,"customer2", CustomerType.PREMIUM, "Mumbai", "9953870879");
		
		Product p1 = new Product(1,ProductType.APPRAEL, BigDecimal.valueOf(1000.0), 10);
		Product p2 = new Product(2,ProductType.GROCERRY, BigDecimal.valueOf(200.0), 20);
		Product p3 = new Product(3,ProductType.FRUIT, BigDecimal.valueOf(500.0), 50);
		Product p4 = new Product(4,ProductType.FITNESSE, BigDecimal.valueOf(700.0), 30);
		Product p5 = new Product(5,ProductType.VEGETABLE, BigDecimal.valueOf(900.0), 100);
		
		List<Product> productList = new ArrayList<>();
		productList.add(p1);
		productList.add(p2);
		productList.add(p3);
		productList.add(p4);
		productList.add(p5);
		new ProductService().addProducts(productList);
		
		ShoppingCartRepository shoppingCartRepository = new ShoppingCartRepository(CREATION_DATE);
        ProductService productService = new ProductService();
        productService.addProducts(productList);
        
	    List<Discount> discounts = new ArrayList<>();
	    
	    RegularCustomerDiscount regularCustomerDiscount1 = new RegularCustomerDiscount(0, new BigDecimal(0), new BigDecimal(5000));
	    RegularCustomerDiscount regularCustomerDiscount2 = new RegularCustomerDiscount(10, new BigDecimal(5000), new BigDecimal(10000));
	    RegularCustomerDiscount regularCustomerDiscount3 = new RegularCustomerDiscount(20, new BigDecimal(10000), new BigDecimal(Integer.MAX_VALUE));
	    discounts.add(regularCustomerDiscount1);
	    discounts.add(regularCustomerDiscount2);
	    discounts.add(regularCustomerDiscount3);
	    
	    PremiumCustomerDiscount premiumCustomerDiscount1 = new PremiumCustomerDiscount(10, new BigDecimal(0), new BigDecimal(4000));
	    PremiumCustomerDiscount premiumCustomerDiscount2 = new PremiumCustomerDiscount(15, new BigDecimal(4000), new BigDecimal(8000));
	    PremiumCustomerDiscount premiumCustomerDiscount3 = new PremiumCustomerDiscount(20, new BigDecimal(8000), new BigDecimal(12000));
	    PremiumCustomerDiscount premiumCustomerDiscount4 = new PremiumCustomerDiscount(30, new BigDecimal(12000), new BigDecimal(Integer.MAX_VALUE));
	    
	    discounts.add(premiumCustomerDiscount1);
	    discounts.add(premiumCustomerDiscount2);
	    discounts.add(premiumCustomerDiscount3);
	    discounts.add(premiumCustomerDiscount4);
	
	    DiscountCalculator discountCalculator = new DiscountCalculator(discounts);
	    
	    ShoppingCartService shoppingCartService = new ShoppingCartService(productService, shoppingCartRepository, discountCalculator);
	    
	   
	    // Test cases for REGULAR CUSTOMER.
	    System.out.println("************************** Test cases for regular customer **************************************");
	    // Story Test Case 1: Total Bill = 5000
	    shoppingCartService.addItem(c1, p1, 5);
        ShoppingCart shoppingCart = shoppingCartService.cartFor(c1);
        
        System.out.println("Total Bill for customer 1: "+shoppingCart.total());
        List<Discount> applicableDiscounts = shoppingCartService.cartDiscount(c1);
        BigDecimal totalDiscount = shoppingCartService.applyDiscount(c1, applicableDiscounts);
        System.out.println("Total Discount : " +totalDiscount.doubleValue());
        System.out.println("Total Bill After Discount : " +shoppingCart.getFinalBill());
       
        System.out.println("****************************************************************");
        shoppingCartRepository.clearCart(c1);
        // Story Test Case 2: Total Bill = 10000
	    shoppingCartService.addItem(c1, p1, 10);
        shoppingCart = shoppingCartService.cartFor(c1);
        
        System.out.println("Total Bill for customer 1: "+shoppingCart.total());
        applicableDiscounts = shoppingCartService.cartDiscount(c1);
        totalDiscount = shoppingCartService.applyDiscount(c1, applicableDiscounts);
        System.out.println("Total Discount : " +totalDiscount.doubleValue());
        System.out.println("Total Bill After Discount : " +shoppingCart.getFinalBill());
        
        System.out.println("****************************************************************");
        shoppingCartRepository.clearCart(c1);
        // Story Test Case 3: Total Bill = 15000
	    shoppingCartService.addItem(c1, p1, 10);
	    shoppingCartService.addItem(c1, p3, 10);
        shoppingCart = shoppingCartService.cartFor(c1);
        
        System.out.println("Total Bill for customer 1: "+shoppingCart.total());
        applicableDiscounts = shoppingCartService.cartDiscount(c1);
        totalDiscount = shoppingCartService.applyDiscount(c1, applicableDiscounts);
        System.out.println("Total Discount : " +totalDiscount.doubleValue());
        System.out.println("Total Bill After Discount : " +shoppingCart.getFinalBill());
        
        
        // Test cases for REGULAR CUSTOMER.
	    System.out.println("************************** Test cases for Premium customer **************************************");
	    shoppingCartRepository.clearCart(c2);
	    // Story Test Case 1: Total Bill = 5000
	    shoppingCartService.addItem(c2, p1, 4);
        shoppingCart = shoppingCartService.cartFor(c2);
        
        System.out.println("Total Bill for customer 2: "+shoppingCart.total());
        applicableDiscounts = shoppingCartService.cartDiscount(c2);
        totalDiscount = shoppingCartService.applyDiscount(c2, applicableDiscounts);
        System.out.println("Total Discount : " +totalDiscount.doubleValue());
        System.out.println("Total Bill After Discount : " +shoppingCart.getFinalBill());
       
        System.out.println("****************************************************************");
        shoppingCartRepository.clearCart(c2);
        // Story Test Case 2: Total Bill = 10000
	    shoppingCartService.addItem(c2, p1, 8);
        shoppingCart = shoppingCartService.cartFor(c2);
        
        System.out.println("Total Bill for customer 2: "+shoppingCart.total());
        applicableDiscounts = shoppingCartService.cartDiscount(c2);
        totalDiscount = shoppingCartService.applyDiscount(c2, applicableDiscounts);
        System.out.println("Total Discount : " +totalDiscount.doubleValue());
        System.out.println("Total Bill After Discount : " +shoppingCart.getFinalBill());
        
        System.out.println("****************************************************************");
        shoppingCartRepository.clearCart(c2);
        // Story Test Case 3: Total Bill = 15000
	    shoppingCartService.addItem(c2, p1, 8);
	    shoppingCartService.addItem(c2, p3, 8);
        shoppingCart = shoppingCartService.cartFor(c2);
        
        System.out.println("Total Bill for customer 2: "+shoppingCart.total());
        applicableDiscounts = shoppingCartService.cartDiscount(c2);
        totalDiscount = shoppingCartService.applyDiscount(c2, applicableDiscounts);
        System.out.println("Total Discount : " +totalDiscount.doubleValue());
        System.out.println("Total Bill After Discount : " +shoppingCart.getFinalBill());
        
        System.out.println("****************************************************************");
        shoppingCartRepository.clearCart(c2);
        // Story Test Case 3: Total Bill = 15000
	    shoppingCartService.addItem(c2, p1, 10);
	    shoppingCartService.addItem(c2, p3, 20);
        shoppingCart = shoppingCartService.cartFor(c2);
        
        System.out.println("Total Bill for customer 2: "+shoppingCart.total());
        applicableDiscounts = shoppingCartService.cartDiscount(c2);
        totalDiscount = shoppingCartService.applyDiscount(c2, applicableDiscounts);
        System.out.println("Total Discount : " +totalDiscount.doubleValue());
        System.out.println("Total Bill After Discount : " +shoppingCart.getFinalBill());
        
	}
	

	
	
}
