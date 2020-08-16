package ce.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/findUserServlet")
public class FindUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取用户名
        String username = request.getParameter("username");
        //2.调用service层判断用户名是否存在

        //期望服务器响应回的数据格式 {"userExist":true,"msg":"此用户名太受欢迎，请更换一个"}
        //                      {"userExist":false,"msg":"该用户名可用 "}
        Map<String, Object> map = new HashMap<>();
        if ("tom".equals(username)) {
            map.put("userExist", true);
            map.put("msg", "此用户名太受欢迎，请更换一个!");
        } else {
            map.put("userExist", false);
            map.put("msg", "该用户名可用!");
        }
        //将mao转为json
        ObjectMapper mapper = new ObjectMapper();

        response.setContentType("text/html;charset=utf-8");
        // response.setContentType("application/json;charset=utf-8");

        mapper.writeValue(response.getWriter(), map);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
