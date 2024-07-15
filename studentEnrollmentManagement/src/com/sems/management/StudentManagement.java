package com.sems.management;
import com.sems.dao.*;
import com.sems.entity.*;
import java.util.List;
import java.util.Scanner;

public class StudentManagement {
    private static StudentDao studentDao = new StudentImpl();
    private static Scanner scanner = new Scanner(System.in);

    public void manageStudents() {
        int choice;
        do {
            System.out.println("\n=== Manage Students ===");
            System.out.println("1. View All Students");
            System.out.println("2. View Student by ID");
            System.out.println("3. Add New Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    viewAllStudents();
                    break;
                case 2:
                    viewStudentById();
                    break;
                case 3:
                    addNewStudent();
                    break;
                case 4:
                    updateStudent();
                    break;
                case 5:
                    deleteStudent();
                    break;
                case 0:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }

    private void viewAllStudents() {
        List<Student> students = studentDao.findAll();
        students.forEach(System.out::println);
    }

    private void viewStudentById() {
        System.out.print("Enter Student ID: ");
        int studentId = scanner.nextInt();
        Student student = studentDao.findById(studentId);
        if (student != null) {
            System.out.println(student);
        } else {
            System.out.println("Student not found.");
        }
    }

    private void addNewStudent() {
        System.out.print("Enter Name: ");
        String name = scanner.next();
        System.out.print("Enter Email: ");
        String email = scanner.next();
        System.out.print("Enter Major: ");
        String major = scanner.next();
        System.out.print("Enter Enrollment Year: ");
        int enrollmentYear = scanner.nextInt();
        Student student = new Student(0, name, email, major, enrollmentYear);
        studentDao.createStudent(student);
        System.out.println("Student added successfully.");
    }

    private void updateStudent() {
        System.out.print("Enter Student ID to update: ");
        int studentId = scanner.nextInt();
        Student student = studentDao.findById(studentId);
        if (student != null) {
            System.out.print("Enter New Name: ");
            String name = scanner.next();
            System.out.print("Enter New Email: ");
            String email = scanner.next();
            System.out.print("Enter New Major: ");
            String major = scanner.next();
            System.out.print("Enter New Enrollment Year: ");
            int enrollmentYear = scanner.nextInt();
            student = new Student(studentId, name, email, major, enrollmentYear);
            studentDao.updateStudent(student);
            System.out.println("Student updated successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    private void deleteStudent() {
        System.out.print("Enter Student ID to delete: ");
        int studentId = scanner.nextInt();
        studentDao.deleteStudent(studentId);
        System.out.println("Student deleted successfully.");
    }
}
