<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>foreach标签</title>
</head>
<body>
<%
    List<Object> list = new ArrayList<>();
    list.add("a");
    list.add("b");
    list.add("c");
    request.setAttribute("list", list);
%>

<c:forEach begin="1" end="10" step="1" var="i" varStatus="s">
    ${i}  index:${s.index}  count:${s.count}<br/>
</c:forEach>
<hr/>
<c:forEach items="${list}" var="l" varStatus="s">
    ${s.index}   ${s.count}    ${l}<br/>
</c:forEach>
</body>
</html>
