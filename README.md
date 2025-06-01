# Student Management System

A simple console-based Student Management System implemented in Java with MySQL database integration.  
The application supports basic CRUD (Create, Read, Update, Delete) operations on student records via a user-friendly command-line interface.

---

## Features

- Add new student records (ID, Name, Age, Course)  
- View all student records  
- Update existing student information  
- Delete student records  
- Search students by name or course

---

## Technologies Used

- Java (JDK 8 or above)  
- MySQL database  
- JDBC for database connectivity  
- Console-based user interface

---

## Setup Instructions

### Prerequisites

- Java Development Kit (JDK) installed  
- MySQL server installed and running  
- IntelliJ IDEA (Community Edition is sufficient) or any Java IDE

### Database Setup

1. Create a MySQL database named `student_db`:

CREATE DATABASE student_db;
USE student_db;


Create the students table without auto-increment:

CREATE TABLE students (
    id INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age INT,
    course VARCHAR(100)
);
Running the Project
Clone the repository:

git clone https://github.com/YOUR_USERNAME/YOUR_REPOSITORY.git
Open the project in IntelliJ IDEA.

Update the database connection credentials in StudentManagementSystem.java:

private static final String DB_USER = "root";
private static final String DB_PASSWORD = "your_mysql_password";

Build and run the Main class.
