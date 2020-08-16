<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>el1</title>
</head>
<body>
<%
    //在域中存储数据
    request.setAttribute("name","zhangsan");
    session.setAttribute("age", "23");
    session.setAttribute("name","lisi");
%>
<h3>EL获取值</h3>
${requestScope.name}<br/>
${sessionScope.age}<br/>
${name}
</body>
</html>
