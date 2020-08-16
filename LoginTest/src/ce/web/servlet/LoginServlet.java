package ce.web.servlet;

import ce.dao.UserDao;
import ce.domain.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/loginDemo")
public class LoginServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.设置编码
        req.setCharacterEncoding("UTF-8");


        //2.获取所有请求参数  BeanUtils
        Map<String, String[]> map = req.getParameterMap();
        //3.创建user对象
        User loginUser=new User();
        //3.2使用BeanUtils封装
        try {
            BeanUtils.populate(loginUser,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //4.调用UserDao的login方法
        UserDao userDao = new UserDao();
        User user = userDao.login(loginUser);
        //5.判断
        if (user != null) {
            //登陆成功
            req.setAttribute("user", user);
            req.getRequestDispatcher("/successServlet").forward(req, resp);
        } else {
            //登陆失败
            req.getRequestDispatcher("/failServlet").forward(req, resp);
        }
    }
}
