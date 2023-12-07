<%--
  Created by IntelliJ IDEA.
  User: ngocthu6778gmail.com
  Date: 2023/12/04
  Time: 22:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create New Student</title>
</head>
<body>
<%--<%=request.getContextPath()%>: lấy đường dẫn gốc, tức là 8080 rồi tiếp theo đến /StudentController?action=create--%>
    <form action="<%=request.getContextPath()%>/StudentController?action=create" method="post">
        <label for="studentName">Student Name</label>
        <%--id="studentName": định danh cho phía client khi css, js --%>
        <input type="text" id="studentName" name="studentName"><br> <%--name="studentName":định danh cho phía server khi nào mình submit form thì servlet nó sẽ nhận và get theo name  --%>
        <label for="age">Age</label>
        <input type="number" id="age" name="age"><br>
        <label for="birthday">Birth Date</label>
        <input type="date" id="birthday" name="birthday"><br>
        <label for="active">Status</label>
        <input type="radio" id="active" name="status" checked value="true"><label for="active">active</label>
        <input type="radio" id="inactive" name="status" value="false"><label for="inactive">inactive</label>
        <input type="submit" value="Create">
    </form>
</body>
</html>
