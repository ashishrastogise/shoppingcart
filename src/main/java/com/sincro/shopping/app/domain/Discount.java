package com.sincro.shopping.app.domain;

import java.math.BigDecimal;

public interface Discount{

	float percentage();
	
	BigDecimal minAmount();
	
	BigDecimal maxamount();
	
	boolean isApplicableTo(ShoppingCart cart, CustomerType customerType);
}