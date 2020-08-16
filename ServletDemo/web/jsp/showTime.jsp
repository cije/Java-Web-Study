<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.net.URLDecoder" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>showTime</title>
</head>
<body>
<%
    //获取当前时间
    LocalDateTime localDateTime = LocalDateTime.now();
    DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy年MM月dd日 hh:mm:ss");
    String now = df.format(localDateTime);
    System.out.println("编码前的时间：" + now);
    //URL编码
    now = URLEncoder.encode(now, "utf-8");
    System.out.println("编码后的时间：" + now);

    //获取所有的Cookie
    Cookie[] cookies = request.getCookies();
    //没有lastTimeCookie
    boolean flag = false;
    if (cookies != null && cookies.length > 0) {
        for (Cookie cookie : cookies) {
            //获取cookie的名称
            String name = cookie.getName();
            //4.判断名称是否是lastTime
            if ("lastTime".equals(name)) {
                //有该cookie 不是第一次访问
                flag = true;
                //响应数据
                //获取cookie的值 时间
                String value = cookie.getValue();
                System.out.println("解码前的时间：" + value);
                //URL解码
                value = URLDecoder.decode(value, "utf-8");
                System.out.println("解码前的时间：" + value);

%>
<h1 align='center'>您好，欢迎回来，您上次访问时间为：<%=value%>
</h1>

<%
                //设置cookie的value
                cookie.setValue(now);

                //设置Cookie的存活时间 一个月
                cookie.setMaxAge(60 * 60 * 24 * 30);

                response.addCookie(cookie);
                break;
            }
        }
    }
    if (cookies == null || cookies.length == 0 || !flag) {

        Cookie cookie = new Cookie("lastTime", now);

        //设置Cookie的存活时间 一个月
        cookie.setMaxAge(60 * 60 * 24 * 30);

        response.addCookie(cookie);
%>

<h1 align='center'>您好，欢迎首次访问</h1>
<%
    }
%>
</body>
</html>
