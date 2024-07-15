package com.sems.dao;
import com.sems.entity.Course;
import java.util.List;

public interface CourseDao {
    List<Course> findAll();
    Course findById(int courseId);
    int createCourse(Course course);
    int updateCourse(Course course);
    int deleteCourse(int courseId);
}
