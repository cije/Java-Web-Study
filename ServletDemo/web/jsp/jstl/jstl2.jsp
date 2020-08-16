<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>choose标签</title>
</head>
<body>
<%
    request.setAttribute("num", 3);
%>
<c:choose>
    <c:when test="${num==1}">
        星期一
    </c:when>
    <c:when test="${num==2}">
        星期二
    </c:when>
    <c:when test="${num==3}">
        星期三
    </c:when>
    <c:when test="${num==4}">
        星期四
    </c:when>
    <c:when test="${num==5}">
        星期五
    </c:when>
    <c:when test="${num==6}">
        星期六
    </c:when>
    <c:when test="${num==7}">
        星期天
    </c:when>
    <c:otherwise>
        数字输入有误
    </c:otherwise>
</c:choose>
</body>
</html>
