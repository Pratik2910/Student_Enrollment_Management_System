# Student Enrollment Management System

## Introduction

This is a console-based application developed in Java to manage student enrollments. 
The application allows you to manage student details, courses, and course enrollments.
It uses MySQL as the database and JDBC for database interactions.

## Requirements

- Java Development Kit (JDK) 8 or higher
- MySQL Database
- MySQL JDBC Driver (Connector/J)

## Database Setup

1. Install MySQL and create a database named `studentmanagement`.

2. Execute the following SQL statements to create the necessary tables:

    ```sql
    CREATE DATABASE studentmanagement;

    USE university;

    CREATE TABLE Student (
        student_id INT AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR(100),
        email VARCHAR(100),
        major VARCHAR(50),
        enrollment_year INT
    );

    CREATE TABLE Course (
        course_id INT AUTO_INCREMENT PRIMARY KEY,
        course_name VARCHAR(100),
        instructor VARCHAR(100),
        credits INT
    );

    CREATE TABLE Enrollment (
        enrollment_id INT AUTO_INCREMENT PRIMARY KEY,
        student_id INT,
        course_id INT,
        enrollment_date DATE,
        status VARCHAR(20),
        FOREIGN KEY (student_id) REFERENCES Student(student_id),
        FOREIGN KEY (course_id) REFERENCES Course(course_id)
    );
    ```

## Project Setup

1. Clone the repository:

    ```bash
    git clone https://github.com/Pratik2910/Student_Enrollment_Management_System
    ```

2. Open the project in your favorite IDE (e.g., IntelliJ IDEA, Eclipse).

3. Add the MySQL JDBC Driver to the project dependencies.

4. Update the database connection details in `DbUtil.java`:

    ```java
    private static final String URL = "jdbc:mysql://localhost:3306/studentmanagement"; 
    private static final String USER = "root";
    private static final String PASSWORD = "password";
    ```

5. Build and run the project.

## Usage

1. Run the `Main` class to start the application.

2. Follow the menu options to manage students, courses, and enrollments.

## Author

Pratik Rajurkar

