package servletDemo.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

//@WebFilter("/*")
public class FilterDemo2 implements Filter {

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("filterDemo2 执行了");

        //对request对象进行增强
        chain.doFilter(req, resp);
        //对response对象进行增强

        System.out.println("filterDemo2 又回来了");

    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}
