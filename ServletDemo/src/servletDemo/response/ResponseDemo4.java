package servletDemo.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/responseDemo4")
public class ResponseDemo4 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //告诉浏览器，服务器发送的消息体数据的编码。建议浏览器使用此编码解码
        resp.setContentType("text/html;charset=utf-8");
        //1.  获取字符输出流
        PrintWriter pw = resp.getWriter();
        //2.  输出数据
//        pw.write("<h1>hello response</h1>");
        pw.write("你好");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
