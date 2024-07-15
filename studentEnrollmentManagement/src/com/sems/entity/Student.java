package com.sems.entity;

public class Student 
{
    private int studentId;
    private String name;
    private String email;
    private String major;
    private int enrollmentYear;

    public Student(int studentId, String name, String email, String major, int enrollmentYear) {
        this.studentId = studentId;
        this.name = name;
        this.email = email;
        this.major = major;
        this.enrollmentYear = enrollmentYear;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMajor() {
        return major;
    }

    public int getEnrollmentYear() {
        return enrollmentYear;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", major='" + major + '\'' +
                ", enrollmentYear=" + enrollmentYear +
                '}';
    }
}
