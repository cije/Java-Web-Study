package servletDemo.servletcontext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.File;

@WebServlet("/servletContextDemo5")
public class ServletContextDemo5 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取ServletContext
        ServletContext context = this.getServletContext();
        //获取文件的服务器路径
        //web目录下的资源
        String c = context.getRealPath("/c.txt");
        System.out.println(c);
//        File file = new File(realPath);
        //WEB-INF目录下的资源
        String b = context.getRealPath("/WEB-INF/b.txt");
        System.out.println(b);
        //src目录下的资源
        String a = context.getRealPath("/WEB-INF/classes/a.txt");
        System.out.println(a);
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
