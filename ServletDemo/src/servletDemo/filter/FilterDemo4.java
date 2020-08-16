package servletDemo.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

//@WebFilter("/index.jsp*")
//@WebFilter("/index.jsp*")
public class FilterDemo4 implements Filter {

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("filterdemo4....");
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }

}
