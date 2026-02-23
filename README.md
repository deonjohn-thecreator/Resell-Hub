# Resell-Hub
A desktop-based Product Reselling Management System built using Java Swing. This application allows users to list products for resale and enables other users to browse and add items to their cart.  The system includes role-based authentication, a modern dashboard with sidebar navigation, and structured product management.

# TEAM MEMBERS:Abhinand Ajikumar(24UBC101)
#            :Deon John(24ubc127)


Project Title:
ResellHub – Desktop-Based Product Reselling Platform Using Java Swing

 Problem Statement

Many individuals want to resell used or second-hand products (vehicles, accessories, etc.), but there is no simple desktop-based system that allows sellers to list products and buyers to browse and purchase efficiently.

This project solves that problem by creating a structured Reselling Management System with role-based access control and a modern graphical interface.

 Objective

To design a desktop-based reselling platform using Java.

To implement role-based login (Admin/Seller and Viewer/Buyer).

To allow sellers to list products for resale.

To allow buyers to browse products and add them to their cart.

To demonstrate GUI design and OOP concepts in Java.

# Features:
 Role-Based Login

Admin (Seller) access

Viewer (Buyer) access

Credential validation

 Modern Dashboard

Sidebar navigation

Category-based browsing

Clean and structured GUI

 Product Categories:

Cars

Bikes

Others

 Cart System:

Add to cart

View cart items

Calculate total price

User-specific cart storage

 Admin Panel:

Add new products for resale

Manage marketplace listings

🛠 Technologies Used

Java

Java Swing (GUI Development)

AWT Layout Managers

Java Collections Framework (HashMap, ArrayList)

Object-Oriented Programming (OOP)

Project Structure:
ResellHub
1) Main.java
2)Login.java
3) Dashboard.java
4) AdminPanel.java
5) ProductPage.java
6) CartPage.java
7) UserDatabase.java
8) ProductDatabase.java
9) CartDatabase.java

   
 Steps to Run the Program:

Install Java (JDK 8 or higher).

Open the project in IntelliJ IDEA / Eclipse.

Ensure all .java files are inside the src folder.

Run Main.java.

Login using the provided credentials.

 Sample Login Credentials:
Admin (Seller)
Username: admin
Password: admin123
Viewer (Buyer)
Username: user
Password: user123

 Sample Test Cases:
Test Case 1 – Admin Adding Product

Input:

Product Name: Honda City

Category: CARS

Price: 500000

Seller: John

Phone: 9876543210

Email: john@email.com

Output:

Product successfully added to Cars category.

Test Case 2 – Viewer Adding to Cart

Action:

Viewer selects a product from Bikes category.

Clicks “Add to Cart”.

Output:

Product appears in cart.

Total price updated.
