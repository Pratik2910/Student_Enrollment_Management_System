package com.sems.dao;
import com.sems.entity.Student;
import com.sems.utils.DbUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentImpl implements StudentDao {
    private static final String SELECT_ALL = "SELECT * FROM student";
    private static final String SELECT_BY_ID = "SELECT * FROM student WHERE student_id = ?";
    private static final String INSERT_QUERY = "INSERT INTO student (name, email, major, enrollment_year) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE student SET name = ?, email = ?, major = ?, enrollment_year = ? WHERE student_id = ?";
    private static final String DELETE_QUERY = "DELETE FROM student WHERE student_id = ?";
    private Connection connection = DbUtil.getConnection();

    @Override
    public List<Student> findAll() {
        List<Student> students = new ArrayList<>();
        try (Statement statement = connection.createStatement(); ResultSet rs = statement.executeQuery(SELECT_ALL)) {
            while (rs.next()) {
                Student student = new Student(
                        rs.getInt("student_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("major"),
                        rs.getInt("enrollment_year")
                );
                students.add(student);
            }
        } catch (SQLException e) {
            System.out.println("Select error: " + e.getMessage());
        }
        return students;
    }

    @Override
    public Student findById(int studentId) {
        Student student = null;
        try (PreparedStatement ps = connection.prepareStatement(SELECT_BY_ID)) {
            ps.setInt(1, studentId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    student = new Student(
                            rs.getInt("student_id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("major"),
                            rs.getInt("enrollment_year")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Select by ID error: " + e.getMessage());
        }
        return student;
    }

    @Override
    public int createStudent(Student student) {
        int result = 0;
        try (PreparedStatement ps = connection.prepareStatement(INSERT_QUERY)) {
            ps.setString(1, student.getName());
            ps.setString(2, student.getEmail());
            ps.setString(3, student.getMajor());
            ps.setInt(4, student.getEnrollmentYear());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Insert error: " + e.getMessage());
        }
        return result;
    }

    @Override
    public int updateStudent(Student student) {
        int result = 0;
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_QUERY)) {
            ps.setString(1, student.getName());
            ps.setString(2, student.getEmail());
            ps.setString(3, student.getMajor());
            ps.setInt(4, student.getEnrollmentYear());
            ps.setInt(5, student.getStudentId());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Update error: " + e.getMessage());
        }
        return result;
    }

    @Override
    public int deleteStudent(int studentId) {
        int result = 0;
        try (PreparedStatement ps = connection.prepareStatement(DELETE_QUERY)) {
            ps.setInt(1, studentId);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Delete error: " + e.getMessage());
        }
        return result;
    }
}
