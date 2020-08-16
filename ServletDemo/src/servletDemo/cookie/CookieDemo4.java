package servletDemo.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Cookie快速入门<br/>
 * `setMaxAge(int seconds)`<br/>
 *
 * 1.  正数：将Cookie数据写到硬盘的文件中。持久化存储。seconds代表存活时间<br/>
 * 2.  负数：默认值<br/>
 * 3.  零：删除cookie信息
 */
@WebServlet("/cookieDemo4")
public class CookieDemo4 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.创建Cookie对象
        Cookie c1 = new Cookie("msg", "setMaxAge");
        //2.设置Cookie 的存活时间
        // 将cookie 持久化存储 到硬盘30秒
        c1.setMaxAge(30);
        //2.发送Cookie
        response.addCookie(c1);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
