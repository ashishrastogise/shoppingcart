# shoppingcart

This project is used to calculate discount on shopping cart based upon customer type and different discount slabs.

Prerequisite:
  JAVA 1.8
  Maven

Command to run with test cases:
  mvn clean install

Project Structure:
  1. All main soruce java files: src/main/java/*
  2. All JUnit Test Cases files: src/test/java/*
  3. Sincro Specific story test data and main class: src/test/java/com/sincro/test/TestApp.java

Sample Input:

Regular Type Customer :

    Customer c1 = new Customer(1,"customer1", CustomerType.REGULAR, "Delhi", "9953870879");

Premium Type Customer:

    Customer c2 = new Customer(2,"customer2", CustomerType.PREMIUM, "Mumbai", "9953870879");
		
Sample products to be added in cart.   

      Product p1 = new Product(1,ProductType.APPRAEL, BigDecimal.valueOf(1000.0), 10);
      Product p2 = new Product(2,ProductType.GROCERRY, BigDecimal.valueOf(200.0), 20);
      Product p3 = new Product(3,ProductType.FRUIT, BigDecimal.valueOf(500.0), 50);
      Product p4 = new Product(4,ProductType.FITNESSE, BigDecimal.valueOf(700.0), 30);
      Product p5 = new Product(5,ProductType.VEGETABLE, BigDecimal.valueOf(900.0), 100);

Sample Output based upon story test cases:

************************** Test cases for regular customer ************

    Total Bill for customer 1: 5000.0
    Total Discount : 0.0
    Total Bill After Discount : 5000

****************************************************************

    Total Bill for customer 1: 10000.0
    Total Discount : 500.0
    Total Bill After Discount : 9500
    
****************************************************************

    Total Bill for customer 1: 15000.0
    Total Discount : 1500.0
    Total Bill After Discount : 13500

************************** Test cases for Premium customer *******************

    Total Bill for customer 2: 4000.0
    Total Discount : 400.0
    Total Bill After Discount : 3600
****************************************************************

    Total Bill for customer 2: 8000.0
    Total Discount : 1000.0
    Total Bill After Discount : 7000
****************************************************************

    Total Bill for customer 2: 12000.0
    Total Discount : 1800.0
    Total Bill After Discount : 10200
****************************************************************

    Total Bill for customer 2: 20000.0
    Total Discount : 4200.0
    Total Bill After Discount : 15800
