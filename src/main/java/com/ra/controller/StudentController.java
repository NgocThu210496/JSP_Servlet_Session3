package com.ra.controller;

import com.ra.model.Student;
import com.ra.service.IStudentService;
import com.ra.servideImp.StudentServiceImp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "StudentController", value = "/StudentController")
public class StudentController extends HttpServlet {
    // muốn lấy dữ liệu thì từ controller gọi sang servide để lấy all sinh viên ở trong database về
    // thì phải tạo 1 đối tượng
    private IStudentService studentService;
    // tao contructor

    public StudentController() {
        studentService = new StudentServiceImp();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String action=request.getParameter("action");
       if(action !=null && action.equals("initUpdate")){
        // lấy thông tin student theo Id để hiển thị cho người dùng cập nhật
           //b1: lấy studentId từ request
           int studentId = Integer.parseInt(request.getParameter("studentId"));
           //b2: gọi studentService lấy student qua id
           Student studentUpdate = studentService.getStudentById(studentId);
           //b3: chuyển dữ liệu sang updateStudent.jsp để hiển thị
           request.setAttribute("updateStudent",studentUpdate); //"updateStudent": là  chứa tất cả các tham số thông tin sv cần cập nhật
           request.getRequestDispatcher("views/updateStudent.jsp").forward(request,response);
       } else if (action != null && action.equals("delete")) {
           //thuc hien xoa sv
           //b1. lay studentId
           int studentId = Integer.parseInt(request.getParameter("studentId"));
           //b2. goi studentService thuc hien xoa
           boolean result = studentService.deleteStudent(studentId);
           //b3. goi getAllStudent de hien thi du lieu
            if(result){
                getAllStudent(request,response);
            }else {
                request.getRequestDispatcher("views/error.jsp").forward(request,response);
            }
           
       } else {
           getAllStudent(request,response);
       }

    }
    public void getAllStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // sau khi gọi <%response.sendRedirect("StudentController");%>, thì nó sẽ chuyển trực tiếp đến doGet
        // goi sang servide de lay all student
        // studentLists: de hung ket qua
        // 2.7
        List<Student> studentLists = studentService.getAllStudent();
        // chuyen listStudent sang students.jsp bằng cách set listStudents vào requets
        // trong request có 1 tham số (name: studentLists) chưa toàn bộ danh sách sinh viên là: studentLists
        request.setAttribute("studentLists", studentLists);
        //chuyển sang views (students.jsp)
        // chuyển từ controller sang
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/students.jsp");
        //va forward toàn bộ request và response sang students.jsp
        requestDispatcher.forward(request,response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // set request hỗ trợ utf8
        request.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        // để lấy tham số ở request thì : thì lấy tham số có tên là action
        // bước 3: từ browser gọi sang controller
        String action = request.getParameter("action");
        if(action.equals("create")){
            // thuwc hien them moi 1 student
            //1. lay du lieu tu student.jsp và luuw vào1 đối tượng
            Student newStudent = new Student();
            newStudent.setStudentName(request.getParameter("studentName"));
            newStudent.setAge(Integer.parseInt(request.getParameter("age")));
            //vì request.getParameter là kiểu string nên phải  sang java.util.Date
            SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd");
            try {
                newStudent.setBirthDay(simpleDateFormat.parse(request.getParameter("birthday")));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            newStudent.setStatus(Boolean.parseBoolean(request.getParameter("status")));
            //2. goi sang service thuc hien them moi
            boolean result = studentService.createStudent(newStudent);
            //3. goi getAllStudent để lấy lại dữ liệu hiện tại trong  và hiển thị ra students.jsp
            if(result){
                getAllStudent(request, response);
            }else {
                // co loi trong qua trinh thuc hien --> error.jsp
                request.getRequestDispatcher("views/error.jsp").forward(request,response);
            }
        }else if (action.equals("update")){
            //thực hiện cập nhật thông tin
            //b1: lấy thông tin cậpo nhật từ updateStudent.jsp và đẩy nó ra đối tượng updateStudent
            //1. lay du lieu tu updateStudent.jsp và luuw vào1 đối tượng student
            Student updateStudent = new Student();
            updateStudent.setStudentId(Integer.parseInt(request.getParameter("studentId")));
            updateStudent.setStudentName(request.getParameter("studentName"));
            updateStudent.setAge(Integer.parseInt(request.getParameter("age")));
            //vì request.getParameter là kiểu string nên phải  sang java.util.Date
            SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd");
            try {
                updateStudent.setBirthDay(simpleDateFormat.parse(request.getParameter("birthday")));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            updateStudent.setStatus(Boolean.parseBoolean(request.getParameter("status")));
            //sau khi lấy được updateStudent rồi thì
            //b2: gọi sang studentService thực hiện cập nhật
            boolean result = studentService.updateStudent(updateStudent);
            //b3: gọi getAllStudent hiển thị danh sách sinh  viên sau khi cập nhật
            if(result){
                getAllStudent(request,response);
            }else {
                request.getRequestDispatcher("views/error.jsp").forward(request,response);
            }
        }

    }
}