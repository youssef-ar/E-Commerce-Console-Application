# java E-Commerce Console Application
## Overview
___

This Java console application mimics an e-commerce management system, providing users with tools to oversee accounts, products, orders, and shopping carts
using object-oriented programming principles.

## Features
___
### 1. User Authentication:
* Implements login/logout functionality for Admins and Customers.
* Accounts are managed by an AccountManager class that gives every account a unique id 


### 2. Product Management
* The Product class serves as a blueprint for representing and managing individual products with attributes such as name, description, price and rating...


### 3. Shopping Cart
* Customers can add, remove or update the quantity of products in their cart
* Handles scenarios where products are out of stock when checking out
* Facilitates order completion from the cart and updates product's stock quantity post purchase.
### 4. Order Processing
* allows customers to place orders from their shopping cart.
* Completed Orders are added to the order history
* provides the estimated delivery date and the order status
### 5. Inventory Management
* Inventory tracking is automated and the stock quantity of products is updated after every placed or cancelled order
* Products that are out of stock are highlighted when displaying inventory
* Admins have special access to inventory allowing them to add and remove products using a public InventoryAccess class
    with a private constructor so that only an Admin can construct it
    and use it as a "signature security"
### 6. Dynamic Product Search and Filtering
* Customers can display all products at once
* Customers can search for products based on name, category or description
* Customers can filter their search by price,category, stock state or rating

### 7. Payment Processing
* Customers are able to pay using a credit/debit card or PayPal

### 8. User Reviews and Ratings
* Customers can review and rate the product but only after they buy it
* reviews and ratings are displayed with the product

### 9. Shipping Options
* multiple shipping options are available with different prices and estimated delivery time

### 10. Order cancellation
* Customers are allowed to cancel an order before it's delivered 

