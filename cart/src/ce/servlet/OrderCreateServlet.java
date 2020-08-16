package ce.servlet;

import ce.bean.Order;
import ce.bean.OrderItem;
import ce.bean.User;
import ce.dao.OrderDao;
import ce.dao.OrderItemDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * OrderCreateServlet创建订单的Servlet<br/>
 * <ol>
 * <li>首选判断用户是否登陆，如果没有登陆跳转到登陆页面</li>
 * <li>创建一个订单对象，并设置其所属用户</li>
 * <li>把该订单对象保存到数据库中</li>
 * <li>遍历session中所有的订单项，设置他们的Order。 然后保存到数据库中</li>
 * <li>清空session中的订单项</li>
 * <li>最后打印订单创建成功</li>
 * </ol>
 */
@WebServlet(urlPatterns = "/createOrder")
public class OrderCreateServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (null == user) {
            resp.sendRedirect("login.jsp");
            return;
        }
        Order order = new Order();
        order.setUser(user);
        new OrderDao().insert(order);
        List<OrderItem> ois = (List<OrderItem>) session.getAttribute("ois");
        for (OrderItem oi : ois) {
            oi.setOrder(order);
            new OrderItemDao().insert(oi);
        }
        ois.clear();
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().println("订单创建成功");
    }
}
