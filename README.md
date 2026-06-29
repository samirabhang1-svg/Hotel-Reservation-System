# 🏨 Hotel Reservation Management System

A **console-based Hotel Reservation Management System** developed using **Core Java, JDBC, and MySQL**. This project allows users to perform complete reservation management through a command-line interface while interacting with a MySQL database.

---

## 📌 Project Overview

The Hotel Reservation Management System is designed to simplify room reservation management. It demonstrates how Java applications communicate with relational databases using JDBC while implementing the CRUD (Create, Read, Update, Delete) operations.

This project helped me strengthen my understanding of:

* Core Java
* JDBC API
* MySQL Database
* SQL Queries
* Exception Handling
* Database Connectivity
* CRUD Operations
* Console-Based Application Development

---

## 🚀 Features

* ✅ Reserve a new hotel room
* ✅ View all reservations
* ✅ Search room number using Reservation ID and Guest Name
* ✅ Update existing reservation details
* ✅ Delete reservation
* ✅ Validate reservation before update/delete
* ✅ Exception handling for database operations

---

## 🛠️ Tech Stack

* **Language:** Java
* **Database:** MySQL
* **Connectivity:** JDBC
* **IDE:** IntelliJ IDEA
---

## 🗄️ Database Schema

### Reservations Table

| Column           | Type                              |
| ---------------- | --------------------------------- |
| reservation_id   | INT (Primary Key, Auto Increment) |
| guest_name       | VARCHAR                           |
| room_no          | INT                               |
| contact_no       | VARCHAR                           |
| reservation_date | TIMESTAMP                         |

---

## ⚙️ Functionalities

### 1. Reserve Room

Creates a new reservation by collecting:

* Guest Name
* Room Number
* Contact Number

The details are stored in the MySQL database.

---

### 2. View Reservations

Displays all reservation records in a formatted table showing:

* Reservation ID
* Guest Name
* Room Number
* Contact Number
* Reservation Date

---

### 3. Get Room Number

Allows users to retrieve the room number by entering:

* Reservation ID
* Guest Name

---

### 4. Update Reservation

Updates an existing reservation after verifying that the Reservation ID exists.

Users can modify:

* Guest Name
* Room Number
* Contact Number

---

### 5. Delete Reservation

Deletes a reservation using the Reservation ID after validation.

---

## 💻 SQL Operations Used

* INSERT
* SELECT
* UPDATE
* DELETE

---

## 📚 JDBC Concepts Used

* Driver Loading
* DriverManager
* Connection
* Statement
* ResultSet
* SQL Execution
* Exception Handling
* Try-with-Resources
---


## 🎯 Learning Outcomes

Through this project, I learned:

* Building Java applications that interact with databases
* Implementing CRUD operations using JDBC
* Writing SQL queries from Java
* Managing database connections
* Handling SQL exceptions
* Structuring a console-based application
* Using try-with-resources for resource management

---

## 👨‍💻 Author

**Samir Abhang**

If you found this project helpful, feel free to ⭐ the repository and connect with me on GitHub.

