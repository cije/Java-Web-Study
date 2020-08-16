package ce.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 敏感词汇过滤
 */
@WebFilter("/*")
public class SensitiveWordsFilter implements Filter {
    /**
     * 敏感词汇集合
     */
    private List<String> list = new ArrayList<>();

    @Override
    public void init(FilterConfig config) throws ServletException {
        try {
            //获取文件的真实路径
            ServletContext servletContext = config.getServletContext();
            String realPath = servletContext.getRealPath("/WEB-INF/classes/敏感词汇.txt");
            //读取文件
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(realPath), "UTF-8"));
            //3.将文件的每一行数据添加到list
            String line = null;
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
            br.close();
            System.out.println(list);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //1.创建代理对象
        ServletRequest proxy_request = (ServletRequest) Proxy.newProxyInstance(req.getClass().getClassLoader(), req.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //增强getParameter方法
                //判断是否是getParameter方法
                String methodName = method.getName();
                if ("getParameter".equals(methodName)) {
                    //增强返回值
                    //获取返回值
                    String value = (String) method.invoke(req, args);
                    if (value != null) {
                        for (String str : list) {
                            if (value.contains(str)) {
                                value = value.replaceAll(str, "***");
                            }
                        }
                    }
                    return value;
                } else if ("getParameterMap".equals(methodName)) {
                    Map<String, String[]> map = (Map<String, String[]>) method.invoke(req, args);
                    Map<String, String[]> newMap = new HashMap<>();
                    if (map != null) {
                        for (String key : map.keySet()) {
                            String[] values = map.get(key);
                            for (int i = 0; i < values.length; i++) {
                                String value = values[i];
                                for (String str : list) {
                                    if (value.contains(str)) {
                                        value = value.replaceAll(str, "***");
                                    }
                                }
                                values[i] = value;
                            }
                            newMap.put(key, values);
                        }
                    }
                    return newMap;
                } else if ("getParameterValues".equals(methodName)) {
                    String[] values = (String[]) method.invoke(req, args);
                    for (int i = 0; i < values.length; i++) {
                        String value = values[i];
                        for (String str : list) {
                            if (value.contains(str)) {
                                value = value.replaceAll(str, "***");
                                values[i] = value;
                            }
                        }
                    }
                    return values;
                } else {
                    return method.invoke(req, args);
                }
            }
        });
        //2.放行
        chain.doFilter(proxy_request, resp);
    }

    @Override
    public void destroy() {

    }

}
