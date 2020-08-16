# Filter：过滤器

- web中的过滤器：当访问服务器的资源时，过滤器可以将请求拦截下来，完成一些特殊的功能。

- 过滤器的作用：

    - 一般用于完成通用的操作。如：登陆验证、统一编码处理、敏感字符过滤...

- 快速入门：

    1.  步骤：

        1.  定义一个类，实现接口Filter
        2.  复写方法
        3.  配置拦截路径

    2.  代码：

        ```java
        @WebFilter(urlPatterns = "/*") //访问所有资源前，都会执行该过滤器
        public class FilterDemo1 implements Filter {
            @Override
            public void init(FilterConfig filterConfig) throws ServletException {
        
            }
        
            @Override
            public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
                System.out.println("filter demo1 被执行...");
        
                //放行
                filterChain.doFilter(servletRequest,servletResponse);
            }
        
            @Override
            public void destroy() {
        
            }
        }
        ```

    3.  过滤器细节

        1.  web.xml配置

            ```xml
            <filter>
            <filter-name>demo1</filter-name>
                <filter-class>FilterDemo1</filter-class>
            </filter>
            <filter-mapping>
            <filter-name>demo1</filter-name>
                <!-- 拦截路径 -->
                <url-pattern>/*</url-pattern>
            </filter-mapping>
            ```  

        2.  过滤器执行流程

            1.  执行过滤器
            2.  执行放行后的资源
            3.  回来执行过滤器放行代码下边的代码

            ```java
            //对request对象进行增强
            chain.doFilter(req, resp);
            //对response对象进行增强
            ```

        3.  过滤器生命周期方法

            1.  `init`：在服务器启动后，会创建Filter对象，然后调用`init`方法。只执行一次，用于加载资源。
            2.  `doFilter`：每一次请求被拦截资源时，会执行。执行多次。
            3.  `destroy`：在服务器关闭后，Filter对象被销毁。如果服务器正常关闭，则会执行`destroy`方法。只执行一次，用于销毁资源。

        4.  过滤器配置详解

            -   拦截路径配置：

                1.  具体资源路径：`/index.jsp` 只有访问 `index.jsp` 资源时过滤器才被执行。
                2.  拦截目录：`/user/*` 访问`/user`下的所有资源时，过滤器会被执行
                3.  后缀名拦截：`*.jsp` 访问所有后缀名为`.jsp`资源时，过滤器会被执行
                4.  拦截所有资源：`/*`  访问所有资源时，过滤器都会被执行。

            -   拦截方式配置：资源被访问的方式

                -   注解配置：

                    -   设置属性`dispatcherTypes`属性
                        1.  `REQUEST`：默认值。浏览器直接请求资源
                        2.  `FORWARD`：转发访问资源
                        3.  `INCLUDE`：包含访问资源
                        4.  `ERROR`：错误跳转资源
                        5.  `ASYNC`：异步访问资源

                -   `web.xml`配置
                    -   设置`<dispatcher></dispatcher>`标签

        5.  过滤器链（配置多个过滤器）

            -   执行顺序：如果有两个过滤器：过滤器1和过滤器2
                1.  过滤器1
                2.  过滤器2
                3.  资源执行
                4.  过滤器2
                5.  过滤器1
            -   过滤器的先后顺序问题：
                1.  注解配置：按照类名的字符串比较规则比较，值小的先执行。
                    -   如：`AFilter` 和 `BFilter`，`Afilter`先执行了 
                2.  `web.xml`配置：谁定义在上边，谁先执行