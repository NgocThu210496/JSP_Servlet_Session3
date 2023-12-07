package com.ra.repository;

import com.ra.model.Student;

import java.util.List;

public interface IStudentRepository {
    //thiết kế 1 phương thức trừu tụowng để quy định phương thức này lấy all
    // dữ từ trong databáe ra
    List<Student> getAllStudent(); //để các lớp kế thừa của nó triển khai ra
    boolean createStudent(Student student);
    Student getStudentById(int studentId);
    boolean updateStudent(Student student);
    boolean deleteStudent(int studentId);
}
