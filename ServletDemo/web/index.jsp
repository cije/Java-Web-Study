<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2020/7/6
  Time: 12:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>index</title>
</head>
<body>
<%
    System.out.println("hello jsp");
    int i = 5;
    String contextPath = request.getContextPath();
    out.println(contextPath);
%>
<%! int i = 3;%>
<%="<br/>Hello JSP"%>
<%
    response.getWriter().write("response");
%>
</body>
</html>
