package com.sems.dao;
import com.sems.entity.Course;
import com.sems.utils.DbUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class CourseImpl implements CourseDao {
    private static final String SELECT_ALL = "SELECT * FROM course";
    private static final String SELECT_BY_ID = "SELECT * FROM course WHERE course_id = ?";
    private static final String INSERT_QUERY = "INSERT INTO course (course_name, instructor, credits) VALUES (?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE course SET course_name = ?, instructor = ?, credits = ? WHERE course_id = ?";
    private static final String DELETE_QUERY = "DELETE FROM course WHERE course_id = ?";
    private Connection connection = DbUtil.getConnection();

    @Override
    public List<Course> findAll() {
        List<Course> courses = new ArrayList<>();
        try (Statement statement = connection.createStatement(); ResultSet rs = statement.executeQuery(SELECT_ALL)) {
            while (rs.next()) {
                Course course = new Course(
                        rs.getInt("course_id"),
                        rs.getString("course_name"),
                        rs.getString("instructor"),
                        rs.getInt("credits")
                );
                courses.add(course);
            }
        } catch (SQLException e) {
            System.out.println("Select error: " + e.getMessage());
        }
        return courses;
    }

    @Override
    public Course findById(int courseId) {
        Course course = null;
        try (PreparedStatement ps = connection.prepareStatement(SELECT_BY_ID)) {
            ps.setInt(1, courseId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    course = new Course(
                            rs.getInt("course_id"),
                            rs.getString("course_name"),
                            rs.getString("instructor"),
                            rs.getInt("credits")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Select by ID error: " + e.getMessage());
        }
        return course;
    }

    @Override
    public int createCourse(Course course) {
        int result = 0;
        try (PreparedStatement ps = connection.prepareStatement(INSERT_QUERY)) {
            ps.setString(1, course.getCourseName());
            ps.setString(2, course.getInstructor());
            ps.setInt(3, course.getCredits());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Insert error: " + e.getMessage());
        }
        return result;
    }

    @Override
    public int updateCourse(Course course) {
        int result = 0;
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_QUERY)) {
            ps.setString(1, course.getCourseName());
            ps.setString(2, course.getInstructor());
            ps.setInt(3, course.getCredits());
            ps.setInt(4, course.getCourseId());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Update error: " + e.getMessage());
        }
        return result;
    }

    @Override
    public int deleteCourse(int courseId) {
        int result = 0;
        try (PreparedStatement ps = connection.prepareStatement(DELETE_QUERY)) {
            ps.setInt(1, courseId);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Delete error: " + e.getMessage());
        }
        return result;
    }
}
