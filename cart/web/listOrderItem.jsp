<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2020/6/26
  Time: 15:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>listOrderItem</title>
</head>
<body>
<h1 align="center">购物车</h1>
<table align="center" border="1" cellspacing="0">
    <tr>
        <td>商品名称</td>
        <td>单价</td>
        <td>数量</td>
        <td>小计</td>
        <td>操作</td>
    </tr>
    <c:forEach items="${ois}" var="oi" varStatus="st">
        <tr>
            <td>${oi.product.name}</td>
            <td>${oi.product.price}</td>
            <td>${oi.num}</td>
            <td>${oi.product.price*oi.num}</td>
            <td><a href="delOrderItem?dname=${oi.product.name}">删除</a></td>
        </tr>
    </c:forEach>
    <c:if test="${!empty ois}">
        <tr>
            <td colspan="4" align="right">
                <a href="createOrder">生成订单</a>
            </td>
        </tr>
    </c:if>
</table>
<%--<div align="center"><a href="listProduct">返回商品列表</a></div>--%>
</body>
</html>
