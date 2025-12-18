
# Library Management System

## Overview

This project is a **Library Management System** implemented in **Java** that manages books, users, and library operations.
It supports multiple user roles (Admin, Librarian, Patron) and uses **CSV files** for data storage instead of a database.

The system focuses on applying **Object-Oriented Programming**, **file handling**, and **GUI development using Java Swing**.

---

## Features

### Admin Module

* Create, update, delete, and search user accounts
* Manage admin credentials
* Add, update, and remove books
* Categorize books by genre, author, and publication year
* Track book status (available, checked out, reserved)

### Librarian Module

* Check out books for patrons
* Handle book returns
* Manage book reservations
* Notify patrons when reserved books become available

### Patron Module

* Create and manage personal accounts
* Search books by title, author, or genre
* View book details and availability
* View checkout history and due dates
* Renew borrowed books and request reservations

### User Module

* Login and logout functionality
* Update personal information

---

## Technologies Used

* Java
* Java Swing (GUI)
* CSV File Handling
* Object-Oriented Programming (OOP)

---

## Data Storage

This project does **not use a database**.
All data is stored using CSV files:

* `Users.csv` → user accounts
* `Library.csv` → book records

---

## Graphical User Interface

* Built using Java Swing
* Separate GUIs for login, signup, admin login, and admin dashboard
* Admin-only access to book and user management features

---

## How to Run

1. Clone the repository
2. Open the project in a Java IDE
3. Run the `Main` class
4. Login as an admin to access the dashboard

---

## Project Objectives

* Apply OOP principles in a real-world system
* Practice file handling without using databases
* Design role-based access control
* Build a functional GUI-based Java application

---

