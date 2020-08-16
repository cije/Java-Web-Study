package ce.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 完成登陆验证的过滤器
 */
@WebFilter("/*")
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //0.强制转换
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        //1.获取资源的请求路径
        String uri = request.getRequestURI();
        //2.判断是否包含登录登陆相关资源路径 要注意排除一些 css js 图片等资源
        if (uri.contains("/index.jsp") || uri.contains("/loginServlet") || uri.contains("/js/") || uri.contains("/css/") || uri.contains("/fonts/") || uri.contains("/checkCodeServlet") || "/case/".equals(uri)) {
            //包含
            chain.doFilter(request, response);
        } else {
            //不包含
            Object user = request.getSession().getAttribute("user");
            if (user != null) {
                //登录了 放行
                chain.doFilter(request, response);
            } else {
                //没有登陆
                request.setAttribute("login_msg", "没有登陆，请登录！");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        }
    }

    @Override
    public void destroy() {

    }

}
