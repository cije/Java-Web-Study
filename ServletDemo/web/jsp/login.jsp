<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <script>
        var refreshImg = function () {
            //1.获取图片对象
            let img = document.getElementById("checkCode");
            //加时间戳
            let date = new Date().getTime();
            img.src = "/ServletDemo/checkCodeServlet?" + date;
        };
    </script>
    <style>
        div {
            color: red;
        }
    </style>
</head>
<body>
<form action="${pageContext.request.contextPath}/loginServlet" method="post">
    <table>
        <tr>
            <td>用户名：</td>
            <td><input type="text" name="username"/></td>
        </tr>
        <tr>
            <td>密码：</td>
            <td><input type="password" name="password"/></td>
        </tr>
        <tr>
            <td>验证码：</td>
            <td><input type="text" name="checkCode"/></td>
        </tr>
        <tr align="center">
            <td colspan="2">
                <img id="checkCode" src="/ServletDemo/checkCodeServlet" onclick="refreshImg()"/>
                <a id="change" onclick="refreshImg();return false;"
                   href="" style="font-size: small">看不清？换一张</a>
            </td>
        </tr>
        <tr align="center">
            <td colspan="2"><input type="submit" value="登录"/></td>
        </tr>
    </table>
</form>
<%--<div><%=request.getAttribute("cc_error")==null?"":request.getAttribute("cc_error")%>
</div>
<div><%=request.getAttribute("login_error")==null?"":request.getAttribute("login_error")%>
</div>--%>
<div>${requestScope.cc_error}</div>
<div>${requestScope.login_error}</div>
</body>
</html>
