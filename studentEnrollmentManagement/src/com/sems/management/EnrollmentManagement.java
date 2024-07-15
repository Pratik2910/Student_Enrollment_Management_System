package com.sems.management;
import com.sems.dao.*;
import com.sems.entity.*;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class EnrollmentManagement {
    private static EnrollmentDao enrollmentDao = new EnrollmentImpl();
    private static Scanner scanner = new Scanner(System.in);

    public void manageEnrollments() {
        int choice;
        do {
            System.out.println("\n=== Manage Enrollments ===");
            System.out.println("1. View All Enrollments");
            System.out.println("2. View Enrollments by Student ID");
            System.out.println("3. View Enrollments by Course ID");
            System.out.println("4. Add New Enrollment");
            System.out.println("5. Update Enrollment");
            System.out.println("6. Delete Enrollment");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    viewAllEnrollments();
                    break;
                case 2:
                    viewEnrollmentsByStudentId();
                    break;
                case 3:
                    viewEnrollmentsByCourseId();
                    break;
                case 4:
                    addNewEnrollment();
                    break;
                case 5:
                    updateEnrollment();
                    break;
                case 6:
                    deleteEnrollment();
                    break;
                case 0:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }

    private void viewAllEnrollments() {
        List<Enrollment> enrollments = enrollmentDao.findAll();
        enrollments.forEach(System.out::println);
    }

    private void viewEnrollmentsByStudentId() {
        System.out.print("Enter Student ID: ");
        int studentId = scanner.nextInt();
        List<Enrollment> enrollments = enrollmentDao.findByStudentId(studentId);
        if (!enrollments.isEmpty()) {
            enrollments.forEach(System.out::println);
        } else {
            System.out.println("No enrollments found for this student.");
        }
    }

    private void viewEnrollmentsByCourseId() {
        System.out.print("Enter Course ID: ");
        int courseId = scanner.nextInt();
        List<Enrollment> enrollments = enrollmentDao.findByCourseId(courseId);
        if (!enrollments.isEmpty()) {
            enrollments.forEach(System.out::println);
        } else {
            System.out.println("No enrollments found for this course.");
        }
    }

    private void addNewEnrollment() {
        System.out.print("Enter Student ID: ");
        int studentId = scanner.nextInt();
        System.out.print("Enter Course ID: ");
        int courseId = scanner.nextInt();
        System.out.print("Enter Enrollment Date (yyyy-mm-dd): ");
        String enrollmentDateStr = scanner.next();
        Date enrollmentDate = Date.valueOf(enrollmentDateStr);
        System.out.print("Enter Status: ");
        String status = scanner.next();
        Enrollment enrollment = new Enrollment(0, studentId, courseId, enrollmentDate, status);
        enrollmentDao.createEnrollment(enrollment);
        System.out.println("Enrollment added successfully.");
    }

    private void updateEnrollment() {
        System.out.print("Enter Enrollment ID to update: ");
        int enrollmentId = scanner.nextInt();
        Enrollment enrollment = enrollmentDao.findById(enrollmentId);
        if (enrollment != null) {
            System.out.print("Enter New Student ID: ");
            int studentId = scanner.nextInt();
            System.out.print("Enter New Course ID: ");
            int courseId = scanner.nextInt();
            System.out.print("Enter New Enrollment Date (yyyy-mm-dd): ");
            String enrollmentDateStr = scanner.next();
            Date enrollmentDate = Date.valueOf(enrollmentDateStr);
            System.out.print("Enter New Status: ");
            String status = scanner.next();
            enrollment = new Enrollment(enrollmentId, studentId, courseId, enrollmentDate, status);
            enrollmentDao.updateEnrollment(enrollment);
            System.out.println("Enrollment updated successfully.");
        } else {
            System.out.println("Enrollment not found.");
        }
    }

    private void deleteEnrollment() {
        System.out.print("Enter Enrollment ID to delete: ");
        int enrollmentId = scanner.nextInt();
        enrollmentDao.deleteEnrollment(enrollmentId);
        System.out.println("Enrollment deleted successfully.");
    }
}
