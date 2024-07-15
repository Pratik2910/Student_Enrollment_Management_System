package com.sems.management;
import com.sems.dao.*;
import com.sems.entity.*;
import java.util.List;
import java.util.Scanner;
public class CourseManagement {
    private static CourseDao courseDao = new CourseImpl();
    private static Scanner scanner = new Scanner(System.in);

    public void manageCourses() {
        int choice;
        do {
            System.out.println("\n=== Manage Courses ===");
            System.out.println("1. View All Courses");
            System.out.println("2. View Course by ID");
            System.out.println("3. Add New Course");
            System.out.println("4. Update Course");
            System.out.println("5. Delete Course");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    viewAllCourses();
                    break;
                case 2:
                    viewCourseById();
                    break;
                case 3:
                    addNewCourse();
                    break;
                case 4:
                    updateCourse();
                    break;
                case 5:
                    deleteCourse();
                    break;
                case 0:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }

    private void viewAllCourses() {
        List<Course> courses = courseDao.findAll();
        courses.forEach(System.out::println);
    }

    private void viewCourseById() {
        System.out.print("Enter Course ID: ");
        int courseId = scanner.nextInt();
        Course course = courseDao.findById(courseId);
        if (course != null) {
            System.out.println(course);
        } else {
            System.out.println("Course not found.");
        }
    }

    private void addNewCourse() {
        System.out.print("Enter Course Name: ");
        String courseName = scanner.next();
        System.out.print("Enter Instructor: ");
        String instructor = scanner.next();
        System.out.print("Enter Credits: ");
        int credits = scanner.nextInt();
        Course course = new Course(0, courseName, instructor, credits);
        courseDao.createCourse(course);
        System.out.println("Course added successfully.");
    }

    private void updateCourse() {
        System.out.print("Enter Course ID to update: ");
        int courseId = scanner.nextInt();
        Course course = courseDao.findById(courseId);
        if (course != null) {
            System.out.print("Enter New Course Name: ");
            String courseName = scanner.next();
            System.out.print("Enter New Instructor: ");
            String instructor = scanner.next();
            System.out.print("Enter New Credits: ");
            int credits = scanner.nextInt();
            course = new Course(courseId, courseName, instructor, credits);
            courseDao.updateCourse(course);
            System.out.println("Course updated successfully.");
        } else {
            System.out.println("Course not found.");
        }
    }

    private void deleteCourse() {
        System.out.print("Enter Course ID to delete: ");
        int courseId = scanner.nextInt();
        courseDao.deleteCourse(courseId);
        System.out.println("Course deleted successfully.");
    }
}
