<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2020/6/26
  Time: 10:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>listProduct</title>
</head>
<body>
<c:if test="${!empty user}">
    <div align="center">
        当前用户: ${user.name}
    </div>
</c:if>
<table align='center' border='1' cellspacing='0'>
    <tr>
        <td>id</td>
        <td>名称</td>
        <td>价格</td>
        <td>购买</td>
    </tr>
    <c:forEach items="${products}" var="product" varStatus="st">
        <tr>
            <td>${product.id}</td>
            <td>${product.name}</td>
            <td>${product.price}</td>
            <td>

<%--                <form action="addOrderItem" method="post" target="ifa">--%>
                <form action="addOrderItem" method="post">

                    数量<input type="text" value="1" name="num"/>
                    <input type="hidden" name="pid" value="${product.id}"/>
<%--                    <input type="submit" value="添加到购物车" />--%>
                    <input type="submit" value="购买" />
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<br/>
<%--<iframe src="" name="ifa" align="right" frameborder="0" width="400px" height="310px"></iframe>--%>
</body>
</html>
