<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>修改信息</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>

</head>
<body>
<div class="container" style="width: 400px;">
    <h3 style="text-align: center;">修改User</h3>
    <form action="${pageContext.request.contextPath}/updateUserServlet" method="post">
        <input type="hidden" name="id" value="${user.id}"/>
        <div class="form-group">
            <label for="name">姓名：</label>
            <input type="text" class="form-control" id="name" name="name" value="${user.name}"
                   placeholder="请输入姓名"/>
        </div>

        <div class="form-group">
            <label>性别：</label>
            <input type="radio" name="gender" value="男"
                    <c:if test="${user.gender=='男'}">
                        checked
                    </c:if>
            />男
            <input type="radio" name="gender" value="女"
                    <c:if test="${user.gender=='女'}">
                        checked
                    </c:if>
            />女
        </div>

        <div class="form-group">
            <label for="age">年龄：</label>
            <input type="text" class="form-control" value="${user.age}" id="age" name="age" placeholder="请输入年龄"/>
        </div>

        <div class="form-group">
            <label for="address">籍贯：</label>
            <select name="address" class="form-control" id="address">
                <option value="陕西"
                        <c:if test="${user.address=='陕西'}">
                            selected
                        </c:if>
                >陕西
                </option>
                <option value="北京"
                        <c:if test="${user.address=='北京'}">
                            selected
                        </c:if>
                >北京
                </option>
                <option value="海南"
                        <c:if test="${user.address=='海南'}">
                            selected
                        </c:if>
                >海南
                </option>
                <option value="天津"
                        <c:if test="${user.address=='天津'}">
                            selected
                        </c:if>
                >天津
                </option>
                <option value="深圳"
                        <c:if test="${user.address=='深圳'}">
                            selected
                        </c:if>
                >深圳
                </option>
                <option value="广东"
                        <c:if test="${user.address=='广东'}">
                            selected
                        </c:if>
                >广东
                </option>
                <option value="湖南"
                        <c:if test="${user.address=='湖南'}">
                            selected
                        </c:if>
                >湖南
                </option>
                <option value="安徽"
                        <c:if test="${user.address=='安徽'}">
                            selected
                        </c:if>
                >安徽
                </option>
            </select>
        </div>

        <div class="form-group">
            <label for="qq">QQ：</label>
            <input id="qq" value="${user.qq}" type="text" class="form-control" name="qq" placeholder="请输入QQ号码"/>
        </div>

        <div class="form-group">
            <label for="email">Email：</label>
            <input id="email" type="text" value="${user.email}" class="form-control" name="email"
                   placeholder="请输入邮箱地址"/>
        </div>

        <div class="form-group" style="text-align: center">
            <input class="btn btn-primary" type="submit" value="提交"/>
            <input class="btn btn-default" type="reset" value="重置"/>
            <input class="btn btn-default" type="button" onclick="window.history.back()" value="返回"/>
        </div>
    </form>
</div>
</body>
</html>
