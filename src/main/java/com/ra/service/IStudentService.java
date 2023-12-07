package com.ra.service;

import com.ra.model.Student;

import java.util.List;

public interface IStudentService {
    List<Student> getAllStudent();
    boolean createStudent(Student student);
    Student getStudentById(int studentId);
    boolean updateStudent(Student student);
    boolean deleteStudent(int studentId);
}
