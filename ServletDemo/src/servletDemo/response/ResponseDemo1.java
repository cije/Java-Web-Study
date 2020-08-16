package servletDemo.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 重定向
 */
@WebServlet(urlPatterns = "/responseDemo1")
public class ResponseDemo1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("responseDemo1.......");
        //访问/responseDemo1会自动跳转到/responseDemo2资源
        /*//1.设置状态吗位302
        resp.setStatus(302);
        //2.设置响应头location
        resp.setHeader("location", "responseDemo2");*/
        req.setAttribute("msg", "response");

        // 动态获取虚拟目录
        String contextPath = req.getContextPath();
        //简单的重定向方法
        resp.sendRedirect(contextPath + "/responseDemo2");
//        resp.sendRedirect("https://www.baidu.com/");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
