package com.sincro.shopping.app.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sincro.shopping.app.domain.Product;

public class ProductService {

	private static Map<Integer, Product> products = new HashMap<Integer, Product>();
    
	public Product productFor(Integer productID) {
		return products.get(productID);
	}

    public boolean hasEnoughItemsInStock(Integer productID, int quantity) {
	    return products.get(productID).getQuantity() >= quantity;
    }
    
    public void addProducts(List<Product> productList) {
    	for (Product product : productList) {
    		products.put(product.getProductID(),product);
		}
    }
}
