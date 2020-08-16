package ce.servlet;

import ce.bean.User;
import ce.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet",urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name=req.getParameter("name");
        String password=req.getParameter("password");
        User user=new UserDao().getUser(name,password);
        if(null==user){
            resp.sendRedirect("");
        }else{
            req.getSession().setAttribute("user",user);
            resp.sendRedirect("listProduct");
        }
    }
}
