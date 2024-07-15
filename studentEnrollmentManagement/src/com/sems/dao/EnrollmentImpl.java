package com.sems.dao;
import com.sems.entity.Enrollment;
import com.sems.utils.DbUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentImpl implements EnrollmentDao {
    private static final String SELECT_ALL = "SELECT * FROM enrollment";
    private static final String SELECT_BY_ID = "SELECT * FROM enrollments WHERE enrollment_id = ?";
    private static final String SELECT_BY_STUDENT_ID = "SELECT * FROM enrollment WHERE student_id = ?";
    private static final String SELECT_BY_COURSE_ID = "SELECT * FROM enrollment WHERE course_id = ?";
    private static final String INSERT_QUERY = "INSERT INTO enrollment (student_id, course_id, enrollment_date, status) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE enrollment SET student_id = ?, course_id = ?, enrollment_date = ?, status = ? WHERE enrollment_id = ?";
    private static final String DELETE_QUERY = "DELETE FROM enrollment WHERE enrollment_id = ?";
    private Connection connection = DbUtil.getConnection();

    @Override
    public List<Enrollment> findAll() {
        List<Enrollment> enrollments = new ArrayList<>();
        try (Statement statement = connection.createStatement(); ResultSet rs = statement.executeQuery(SELECT_ALL)) {
            while (rs.next()) {
                Enrollment enrollment = new Enrollment(
                        rs.getInt("enrollment_id"),
                        rs.getInt("student_id"),
                        rs.getInt("course_id"),
                        rs.getDate("enrollment_date"),
                        rs.getString("status")
                );
                enrollments.add(enrollment);
            }
        } catch (SQLException e) {
            System.out.println("Select error: " + e.getMessage());
        }
        return enrollments;
    }

    @Override
    public List<Enrollment> findByStudentId(int studentId) {
        List<Enrollment> enrollments = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(SELECT_BY_STUDENT_ID)) {
            ps.setInt(1, studentId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Enrollment enrollment = new Enrollment(
                            rs.getInt("enrollment_id"),
                            rs.getInt("student_id"),
                            rs.getInt("course_id"),
                            rs.getDate("enrollment_date"),
                            rs.getString("status")
                    );
                    enrollments.add(enrollment);
                }
            }
        } catch (SQLException e) {
            System.out.println("Select by student ID error: " + e.getMessage());
        }
        return enrollments;
    }

    @Override
    public List<Enrollment> findByCourseId(int courseId) {
        List<Enrollment> enrollments = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(SELECT_BY_COURSE_ID)) {
            ps.setInt(1, courseId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Enrollment enrollment = new Enrollment(
                            rs.getInt("enrollment_id"),
                            rs.getInt("student_id"),
                            rs.getInt("course_id"),
                            rs.getDate("enrollment_date"),
                            rs.getString("status")
                    );
                    enrollments.add(enrollment);
                }
            }
        } catch (SQLException e) {
            System.out.println("Select by course ID error: " + e.getMessage());
        }
        return enrollments;
    }

    @Override
    public int createEnrollment(Enrollment enrollment) {
        int result = 0;
        try (PreparedStatement ps = connection.prepareStatement(INSERT_QUERY)) {
            ps.setInt(1, enrollment.getStudentId());
            ps.setInt(2, enrollment.getCourseId());
            ps.setDate(3, enrollment.getEnrollmentDate());
            ps.setString(4, enrollment.getStatus());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Insert error: " + e.getMessage());
        }
        return result;
    }

    @Override
    public int updateEnrollment(Enrollment enrollment) {
        int result = 0;
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_QUERY)) {
            ps.setInt(1, enrollment.getStudentId());
            ps.setInt(2, enrollment.getCourseId());
            ps.setDate(3, enrollment.getEnrollmentDate());
            ps.setString(4, enrollment.getStatus());
            ps.setInt(5, enrollment.getEnrollmentId());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Update error: " + e.getMessage());
        }
        return result;
    }

    @Override
    public int deleteEnrollment(int enrollmentId) {
        int result = 0;
        try (PreparedStatement ps = connection.prepareStatement(DELETE_QUERY)) {
            ps.setInt(1, enrollmentId);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Delete error: " + e.getMessage());
        }
        return result;
    }
    @Override
    public Enrollment findById(int enrollmentId) {
        Enrollment enrollment = null;
        try (PreparedStatement ps = connection.prepareStatement(SELECT_BY_ID)) {
            ps.setInt(1, enrollmentId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                enrollment = new Enrollment(
                        rs.getInt("enrollment_id"),
                        rs.getInt("student_id"),
                        rs.getInt("course_id"),
                        rs.getDate("enrollment_date"),
                        rs.getString("status")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error finding enrollment: " + e.getMessage());
        }
        return enrollment;
    }
}
