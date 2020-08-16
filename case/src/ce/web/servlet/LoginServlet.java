package ce.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置request编码
        request.setCharacterEncoding("utf-8");
        //2.获取参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String checkCode = request.getParameter("verifycode");
        //3.获取生成的验证码
        HttpSession session = request.getSession();
        String checkCode_session = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");
        //4.判断验证码是否正确 忽略大小写
        if (checkCode_session!=null&&checkCode_session.equalsIgnoreCase(checkCode)) {
            //验证码正确
            //判断用户名和密码是否一致
            if ("admin".equals(username) && "admin".equals(password)) {
                //登陆成功
                //存储信息 用户信息
                session.setAttribute("user", username);
                //重定向到success.jsp
                response.sendRedirect(request.getContextPath() + "/welcome.jsp");
            } else {
                //登陆失败
                //存储提示信息到request
                request.setAttribute("login_msg", "用户名或密码错误");
                request.getRequestDispatcher("/").forward(request, response);
            }
        } else {
            //验证码不正确
            //存储提示信息到request
            request.setAttribute("login_msg", "验证码错误");
            request.getRequestDispatcher("/").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
