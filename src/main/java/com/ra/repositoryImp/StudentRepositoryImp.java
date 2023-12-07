package com.ra.repositoryImp;

import com.ra.model.Student;
import com.ra.repository.IStudentRepository;
import com.ra.util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentRepositoryImp implements IStudentRepository {
    @Override
    public List<Student> getAllStudent() { //nhiệm vụ của phương thức này là lấy database trả về cho repository này
        // khai báo 1 list student để trả về
        List<Student>studentList = null;
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection=ConnectionDB.oppenConnection();
            callableStatement= connection.prepareCall("{call proc_get_all_student()}");
            ResultSet resultSet =callableStatement.executeQuery();
            studentList = new ArrayList<>();
            while (resultSet.next()){
                //mỗi vòng thì khởi tạo 1 đối tượng student
                Student student = new Student();
                // set du lieu vao cho student
                student.setStudentId(resultSet.getInt("student_id"));
                student.setStudentName(resultSet.getString("student_name"));
                student.setAge(resultSet.getInt("age"));
                student.setBirthDay(resultSet.getDate("birth_day"));
                student.setStatus(resultSet.getBoolean("student_status"));
                // add vao list
                studentList.add(student);
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
            ConnectionDB.closeConnection(connection,callableStatement);
        }
        return studentList;
    }


    @Override
    public boolean createStudent(Student student) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        boolean result = false;
        try {
            connection = ConnectionDB.oppenConnection();
            callableStatement= connection.prepareCall("{call proc_create_student(?,?,?,?)}");
            callableStatement.setString(1, student.getStudentName());
            callableStatement.setInt(2,student.getAge());
            // convert tu java.util.Date(của java) sang java.sql.date
            callableStatement.setDate(3,new Date(student.getBirthDay().getTime()));
            callableStatement.setBoolean(4,student.isStatus());
            callableStatement.executeUpdate();
            result=true;

        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(connection,callableStatement);
        }

        return result;
    }

    @Override
    public Student getStudentById(int studentId) {
        // khai báo 1 list student để trả về
        Student student = null;
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection=ConnectionDB.oppenConnection();
            callableStatement= connection.prepareCall("{call proc_get_student_by_id(?)}");
            callableStatement.setInt(1,studentId);
            ResultSet resultSet =callableStatement.executeQuery();
            student = new Student();
            if (resultSet.next()){
                // set du lieu vao cho student
                student.setStudentId(resultSet.getInt("student_id"));
                student.setStudentName(resultSet.getString("student_name"));
                student.setAge(resultSet.getInt("age"));
                student.setBirthDay(resultSet.getDate("birth_day"));
                student.setStatus(resultSet.getBoolean("student_status"));
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
            ConnectionDB.closeConnection(connection,callableStatement);
        }
        return student;
    }

    @Override
    public boolean updateStudent(Student student) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        boolean result = false;
        try {
            connection = ConnectionDB.oppenConnection();
            callableStatement= connection.prepareCall("{call proc_update_student(?,?,?,?,?)}");
            callableStatement.setInt(1,student.getStudentId());
            callableStatement.setString(2, student.getStudentName());
            callableStatement.setInt(3,student.getAge());
            // convert tu java.util.Date(của java) sang java.sql.date
            callableStatement.setDate(4,new Date(student.getBirthDay().getTime()));
            callableStatement.setBoolean(5,student.isStatus());
            callableStatement.executeUpdate();
            result=true;

        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(connection,callableStatement);
        }

        return result;
    }

    @Override
    public boolean deleteStudent(int studentId) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        boolean result = false;
        try {
            connection = ConnectionDB.oppenConnection();
            callableStatement= connection.prepareCall("{call proc_delete_student(?)}");
            callableStatement.setInt(1,studentId);
            callableStatement.executeUpdate();
            result=true;
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(connection,callableStatement);
        }

        return result;
    }
}
