package com.sems.dao;
import com.sems.entity.*;

import java.util.List;

public interface StudentDao 
{
    List<Student> findAll();
    Student findById(int studentId);
    int createStudent(Student student);
    int updateStudent(Student student);
    int deleteStudent(int studentId);
}
