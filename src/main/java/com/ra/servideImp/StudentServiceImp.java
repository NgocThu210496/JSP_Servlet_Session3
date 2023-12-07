package com.ra.servideImp;

import com.ra.model.Student;
import com.ra.repository.IStudentRepository;
import com.ra.repositoryImp.StudentRepositoryImp;
import com.ra.service.IStudentService;

import java.util.List;

public class StudentServiceImp implements IStudentService {
    //từ StudentServiceImp gọi sang repository để lấy dữ liệu,
    // vì vậy phải khởi tạo 1 đối tượng repository để làm việc
    private IStudentRepository studentRepository;

    public StudentServiceImp() {
        // khoi tao tu interface thiet ke (IStudentRepository) va tu lop thuc thi nay StudentRepositoryImp
        studentRepository = new StudentRepositoryImp();
    }

    @Override
    public List<Student> getAllStudent() {
        // studentRepository: la doi tuong duoc tao ow tren
    //từ service này muôsn gọi đến repository để lấy về tất cả sinh viên, thì phải gọi :
        //List<Student> studentList = studentRepository.getAllStudent();  // từ service gọi sang
        //sau khi làm việc xong thì nó nhận kết quả và trả về 1 studentList
        //return studentList; // từ repository trả về cho service

        // hoặc viết cách này:(command 2 dòng trên lại)
        return studentRepository.getAllStudent();

    }

    @Override
    public boolean createStudent(Student student) {

        return studentRepository.createStudent(student);
    }

    @Override
    public Student getStudentById(int studentId) {
        return studentRepository.getStudentById(studentId);
    }

    @Override
    public boolean updateStudent(Student student) {
        return studentRepository.updateStudent(student);
    }

    @Override
    public boolean deleteStudent(int studentId) {
        return studentRepository.deleteStudent(studentId);
    }
}
