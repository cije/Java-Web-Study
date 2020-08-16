package ce.servlet;

import ce.bean.OrderItem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * @author lenovo
 */
@WebServlet(name = "OrderItemDelServlet", urlPatterns = "/delOrderItem")
public class OrderItemDelServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("dname");
        List<OrderItem> ois = (List<OrderItem>) req.getSession().getAttribute("ois");
        Iterator<OrderItem> it = ois.iterator();
        while (it.hasNext()) {
            OrderItem oi = it.next();
            if (name.equals(oi.getProduct().getName())) {
                it.remove();
                break;
            }
        }
        resp.sendRedirect("listOrderItem");
    }
}
