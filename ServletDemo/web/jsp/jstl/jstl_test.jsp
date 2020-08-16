<%@ page import="java.util.List" %>
<%@ page import="domain.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>test</title>
</head>
<body>
<%
    List<User> list = new ArrayList<>();
    list.add(new User("张三", 15, LocalDateTime.now()));
    list.add(new User("王五", 19, LocalDateTime.now()));
    list.add(new User("李四", 25, LocalDateTime.now()));
    request.setAttribute("list", list);
%>
<table border="1" width="500" align="center">
    <tr>
        <th>编号</th>
        <th>姓名</th>
        <th>年龄</th>
        <th>生日</th>
    </tr>
    <c:forEach items="${list}" varStatus="s" var="user">
        <c:if test="${s.count %2!=0}">
            <tr bgcolor="#faebd7">
        </c:if>
        <c:if test="${s.count %2==0}">
            <tr bgcolor="#6495ed">
        </c:if>

            <td>${s.count}</td>
            <td>${user.name}</td>
            <td>${user.age}</td>
            <td>${user.dateStr}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
