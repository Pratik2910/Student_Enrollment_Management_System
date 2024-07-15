package com.sems.main;
import com.sems.management.*;
import java.util.Scanner;

public class EnrollmentManagementApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManagement studentManagement = new StudentManagement();
        CourseManagement courseManagement = new CourseManagement();
        EnrollmentManagement enrollmentManagement = new EnrollmentManagement();

        int choice;
        do {
            System.out.println("\n=== Enrollment Management System ===");
            System.out.println("1. Manage Students");
            System.out.println("2. Manage Courses");
            System.out.println("3. Manage Enrollments");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    studentManagement.manageStudents();
                    break;
                case 2:
                    courseManagement.manageCourses();
                    break;
                case 3:
                    enrollmentManagement.manageEnrollments();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }
}
