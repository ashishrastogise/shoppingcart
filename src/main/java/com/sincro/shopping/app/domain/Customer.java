package com.sincro.shopping.app.domain;

public class Customer {
	int id;
	String name;
	CustomerType customerType;
	String address;
	String contactNumber;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public CustomerType getCustomerType() {
		return customerType;
	}

	public void setCustomerType(CustomerType customerType) {
		this.customerType = customerType;
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Customer(int id, String name, CustomerType customerType, String address, String contactNumber) {
		super();
		this.id = id;
		this.name = name;
		this.customerType = customerType;
		this.address = address;
		this.contactNumber = contactNumber;
	}



}
