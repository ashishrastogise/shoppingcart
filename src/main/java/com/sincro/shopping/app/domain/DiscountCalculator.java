package com.sincro.shopping.app.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ashish
 *
 */
public class DiscountCalculator {

	private List<Discount> discounts = new ArrayList<Discount>();

	public DiscountCalculator(List<Discount> discounts) {
		this.discounts = discounts;
	}

	
	/**
	 * This method is used to find applicable discounts on shopping cart based upon customer type.
	 * 
	 * @param shoppingCart
	 * @param customerType
	 * @return
	 */
	public List<Discount> discountFor(ShoppingCart shoppingCart, CustomerType customerType) {

		Comparator<Discount> compareByMinAndMaxAmount = Comparator.comparing(Discount::minAmount)
											.thenComparing(Discount::maxamount);

		List<Discount> applicableDiscounts =  discounts.stream().filter(discount -> discount.isApplicableTo(shoppingCart, customerType))
				.collect(Collectors.toList());
		
		if(applicableDiscounts != null && applicableDiscounts.size() > 0) {
			Collections.sort(applicableDiscounts, compareByMinAndMaxAmount);
		}else {
			List<Discount> noDiscountList = new ArrayList<>();
			noDiscountList.add(new NoDiscount());
			return noDiscountList;
		}
		return applicableDiscounts;
	}

	/**
	 * This method is used to apply discount on total cart value based upon multiple slabs.
	 * 
	 * @param shoppingCart
	 * @param applicableDiscounts
	 * @return
	 */
	public BigDecimal applyDiscounts(ShoppingCart shoppingCart, List<Discount> applicableDiscounts) {
		double totalDiscount = 0;
		
		double totalBill = shoppingCart.total().doubleValue();
		
		double discountableBill = totalBill;
		
		for (Discount discount : applicableDiscounts) {
			if(totalBill > discount.maxamount().doubleValue()) {
				discountableBill = discount.maxamount().doubleValue() - discount.minAmount().doubleValue();;
			}else if(discount.maxamount().intValue() == Integer.MAX_VALUE) {
				discountableBill = totalBill - discount.minAmount().doubleValue();;
			}
			totalDiscount = totalDiscount + (discountableBill * (discount.percentage()/100));
		}
		
		shoppingCart.setTotalDiscount(new BigDecimal(totalDiscount).setScale(2, BigDecimal.ROUND_HALF_DOWN));
		return new BigDecimal(totalDiscount).setScale(2, BigDecimal.ROUND_HALF_DOWN);
	}

}