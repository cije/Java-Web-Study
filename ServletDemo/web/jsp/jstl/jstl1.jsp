<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>if标签</title>
</head>
<body>
<%--
c:if标签
    1.属性：
        * test 必须属性，接受boolean表达式
            * 如果表达式为true，则显示标签体的内容，否则不显示
            * 一般情况下，test属性值配合EL表达式一起使用
--%>
<c:if test="${true}">
    <h1>
        啦啦啦</h1>
</c:if>

<%
    //判断request域中的一个list集合是否为空，如果不为空，则遍历
    List<Object> list = new ArrayList<>();
    list.add("add");
    request.setAttribute("list", list);
    request.setAttribute("num", 3);
%>
<c:if test="${!empty list}">
    遍历集合
</c:if>
<br/>
<c:if test="${num % 2 ==0}">
    ${num}为偶数
</c:if>
<c:if test="${!(num % 2 ==0)}">
    ${num}为奇数
</c:if>
</body>
</html>
