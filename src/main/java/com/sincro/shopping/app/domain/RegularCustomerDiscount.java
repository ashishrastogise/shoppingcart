package com.sincro.shopping.app.domain;

import java.math.BigDecimal;

/**
 * @author Ashish
 *
 */
public class RegularCustomerDiscount implements Discount{

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
				&& customerType.equals(CustomerType.REGULAR);
	}

	public float getPercentage() {
		return percentage;
	}

	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}

	public BigDecimal getMinAmount() {
		return minAmount;
	}

	public void setMinAmount(BigDecimal minAmount) {
		this.minAmount = minAmount;
	}

	public BigDecimal getMaxAmount() {
		return maxAmount;
	}

	public void setMaxAmount(BigDecimal maxAmount) {
		this.maxAmount = maxAmount;
	}

	public RegularCustomerDiscount(int percentage, BigDecimal minAmount, BigDecimal maxAmount) {
		super();
		this.percentage = percentage;
		this.minAmount = minAmount;
		this.maxAmount = maxAmount;
	}
	
	

}
