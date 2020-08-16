package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    // 声明UserService对象
    private UserService service = new UserServiceImpl();

    /**
     * 注册功能
     */
    public void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //验证码校验
        if (!checkCode(request, response)) {
            return;
        }

        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        boolean flag = service.regist(user);
        ResultInfo info = new ResultInfo();
        info.setFlag(flag);
        if (flag) {
            //注册成功
        } else {
            //失败
            info.setErrorMsg("注册失败！");
        }
        writeValue(info, response);

    }

    /**
     * 登录功能
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //验证码校验
        if (!checkCode(request, response)) {
            return;
        }

        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        // UserService service = new UserServiceImpl();
        ResultInfo info = new ResultInfo();
        int[] login = service.login(user);
        int flag = login[1];
        user.setUid(login[0]);
        if (flag == 0) {
            //用户名不存在
            info.setFlag(false);
            info.setErrorMsg("该用户不存在，请注册！");
        } else if (flag == -1) {
            //未激活
            info.setFlag(false);
            info.setErrorMsg("该用户未激活，请激活后登录！");
        } else if (flag == -2) {
            //密码错误
            info.setFlag(false);
            info.setErrorMsg("密码错误！");
        } else {
            //正常登录
            info.setFlag(true);
            request.getSession().setAttribute("user", user);
        }
        writeValue(info, response);
    }

    /**
     * 查找一个
     */
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从session获取登录用户
        Object user = request.getSession().getAttribute("user");
        writeValue(user, response);
    }

    /**
     * 退出功能
     */
    public void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //销毁session
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath() + "/login.html");
    }

    /**
     * 激活功能
     */
    public void active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        if (code != null && code != "") {
            UserService service = new UserServiceImpl();
            boolean flag = service.active(code);
            String msg = null;
            if (flag) {
                //激活成功
                msg = "激活成功，请<a href='login.html'>登录</a>！";
            } else {
                //激活失败
                msg = "激活失败，请联系管理员！";
            }
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(msg);
        }
    }

    /**
     * 验证码校验
     *
     * @return 通过 true 不通过 false
     */
    private boolean checkCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //验证码校验
        String check = request.getParameter("check");
        HttpSession session = request.getSession();
        String checkCode_Server = (String) session.getAttribute("CHECKCODE_SERVER");
        //保证验证码只能使用一次
        session.removeAttribute("CHECKCODE_SERVER");
        if (checkCode_Server == null || !checkCode_Server.equalsIgnoreCase(check)) {
            //验证码错误
            ResultInfo info = new ResultInfo();
            info.setFlag(false);
            info.setErrorMsg("验证码错误！");
            writeValue(info, response);
            return false;
        }
        return true;
    }
}
