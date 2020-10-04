package com.sincro.shopping.app.domain;

import java.math.BigDecimal;

/**
 * @author Ashish
 *
 */
public class PremiumCustomerDiscount implements Discount{

	private float percentage;
	
	private BigDecimal minAmount;
	
	private BigDecimal maxAmount;
	
	@Override
	public float percentage() {
		return percentage;
	}

	@Override
	public BigDecimal minAmount() {
		return minAmount;
	}

	@Override
	public BigDecimal maxamount() {
		return	maxAmount;
	}

	@Override
	public boolean isApplicableTo(ShoppingCart cart, CustomerType customerType) {
		return (cart.total().doubleValue() >= maxAmount.doubleValue() || (cart.total().doubleValue() > minAmount.doubleValue() && cart.total().doubleValue() < maxAmount.doubleValue())) 
				&& customerType.equals(CustomerType.PREMIUM);
	}

	public PremiumCustomerDiscount(int percentage, BigDecimal minAmount, BigDecimal maxAmount) {
		super();
		this.percentage = percentage;
		this.minAmount = minAmount;
		this.maxAmount = maxAmount;
	}
	
	

}
