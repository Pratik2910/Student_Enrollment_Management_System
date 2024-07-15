package com.sems.dao;
import com.sems.entity.Enrollment;
import java.util.List;

public interface EnrollmentDao 
{
    List<Enrollment> findAll();
    Enrollment findById(int enrollmentId);
    List<Enrollment> findByStudentId(int studentId);
    List<Enrollment> findByCourseId(int courseId);
    int createEnrollment(Enrollment enrollment);
    int updateEnrollment(Enrollment enrollment);
    int deleteEnrollment(int enrollmentId);
}
