<%--
  Created by IntelliJ IDEA.
  User: ngocthu6778gmail.com
  Date: 2023/12/06
  Time: 21:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Student</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/StudentController?action=update" method="post">
    <label for="studentId">StudentId</label>
    <%--value="${updateStudent.studentId}: ở bên StudentController(doGet b3) đang set vào request --%>
    <input type="number" id="studentId" name="studentId" value="${updateStudent.studentId}" readonly><br>
    <label for="studentName">Student Name</label>
    <%--id="studentName": định danh cho phía client khi css, js --%>
    <input type="text" id="studentName" name="studentName" value="${updateStudent.studentName}"><br> <%--name="studentName":định danh cho phía server khi nào mình submit form thì servlet nó sẽ nhận và get theo name  --%>
    <label for="age">Age</label>
    <input type="number" id="age" name="age" value="${updateStudent.age}"><br>
    <label for="birthday">Birth Date</label>
    <input type="date" id="birthday" name="birthday" value="${updateStudent.birthDay}"><br>
    <label for="active">Status</label>
    <input type="radio" id="active" name="status" ${updateStudent.status?'checked':''} value="true"><label for="active">active</label>
    <input type="radio" id="inactive" name="status" ${updateStudent.status?'':'checked'} value="false"><label for="inactive">inactive</label>
    <input type="submit" value="Update">
</form>
</body>
</html>
