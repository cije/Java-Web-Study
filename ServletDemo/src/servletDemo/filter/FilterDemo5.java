package servletDemo.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

//@WebFilter(value = "/*",dispatcherTypes = DispatcherType.REQUEST) // 直接请求
//@WebFilter(value = "/*",dispatcherTypes = DispatcherType.FORWARD) // 转发
@WebFilter(value = "/*", dispatcherTypes = {DispatcherType.FORWARD, DispatcherType.REQUEST}) // 直接请求+转发
public class FilterDemo5 implements Filter {

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }

}
