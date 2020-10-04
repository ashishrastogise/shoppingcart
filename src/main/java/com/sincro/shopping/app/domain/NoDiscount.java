package com.sincro.shopping.app.domain;

import java.math.BigDecimal;

public class NoDiscount implements Discount {

	private int percentage = 0;
	
	private BigDecimal minAmount = new BigDecimal(0);
	
	private BigDecimal maxAmount = new BigDecimal(0);

	@Override
	public float percentage() {
		return percentage;
	}

	@Override
	public boolean isApplicableTo(ShoppingCart cart, CustomerType customerType) {
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

}