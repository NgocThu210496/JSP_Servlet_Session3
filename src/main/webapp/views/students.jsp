<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: ngocthu6778gmail.com
  Date: 2023/12/04
  Time: 20:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List Student</title>
</head>
<body>
  <table border="1">
    <thead>
      <tr>
        <th>No</th>
        <th>Student Id</th>
        <th>Student Name</th>
        <th>Age</th>
        <th>Birth Day</th>
        <th>Status</th>
        <th>Actions</th>
      </tr>
    </thead>
    <tbody>
     <%--duyet foreach de hien thi du lieu--%>
    <%---- lấy tham số có tên là studentLists ở trong requets ra ở trong doGet và đổ vào thuộc tính items này
    -- và mỗi vòng đẩy 1 phần tử items này ra 1 biến có tên là student--%>
     <c:set var="no" value="1"/>
     <c:forEach items="${studentLists}" var="student">
        <tr>
          <td>${no}</td>
          <td>${student.studentId}</td> <%---- viết đúng các thuộc tính của đối tượng--%>
          <td>${student.studentName}</td>
          <td>${student.age}</td>
          <td><fmt:formatDate pattern="dd/MM/yyyy" value="${student.birthDay}"/></td>
          <td>${student.status ? "Active" : "Inactive"}</td>
          <%--truyền vào 2 tham số trên url đó là: action và student. Sau khi truyền xong 2 tham số thì nó sẽ vào doGet của StudentController--%>
          <td>
            <a href="<%=request.getContextPath()%>/StudentController?action=initUpdate&&studentId=${student.studentId}">Update</a>
            <a href="<%=request.getContextPath()%>/StudentController?action=delete&&studentId=${student.studentId}">Delete</a>
          </td>
        </tr>
       <c:set var="no" value="${no+1}"/>
      </c:forEach>
    </tbody>
  </table>
<a href="views/newStudent.jsp">Create New Student</a>
</body>
</html>
