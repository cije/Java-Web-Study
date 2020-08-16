<%@ page import="domain.User" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>el获取数据</title>
</head>
<body>
<%
    User user = new User();
    user.setName("张三");
    user.setAge(23);
    user.setDate(LocalDateTime.now());
    request.setAttribute("user", user);

    List<Object> list=new ArrayList<>();
    list.add("aaa");
    list.add("bbb");
    list.add(user);
    request.setAttribute("list",list);

    Map map=new HashMap();
    map.put("sname","李四");
    map.put("gender","male");
    map.put("user",user);
    request.setAttribute("map",map);
%>
<h3>el获取对象中的值</h3>
${requestScope.user}<br/>
${requestScope.user.name}<br/>
${requestScope.user.date}<br/>
${requestScope.user.date.year}<br/>
${requestScope.user.date.month}<br/>
${requestScope.user.dateStr}<br/>

<%--
    * 通过的是对象的属性来获取
        * setter或getter方法，去掉
        * setName -> Name -> name
--%>

<h3>el获取list中的值</h3>
${list}<br/>
${list[0]}<br/>

${list[10]}<br/>

${list[2].name}<br/>

<h3>el获取map中的值</h3>
${map.gender}<br/>
${map["gender"]}<br/>
${map.user.name}<br/>
</body>
</html>
