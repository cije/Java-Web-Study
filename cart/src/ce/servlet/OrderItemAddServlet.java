package ce.servlet;

import ce.bean.OrderItem;
import ce.bean.Product;
import ce.dao.ProductDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "OrderItemAddServlet", urlPatterns = "/addOrderItem")
public class OrderItemAddServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int num = Integer.parseInt(req.getParameter("num"));
        int pid = Integer.parseInt(req.getParameter("pid"));
        Product product = new ProductDao().getProduct(pid);
        OrderItem oi = new OrderItem();
        oi.setNum(num);
        oi.setProduct(product);
        List<OrderItem> ois = (List<OrderItem>) req.getSession().getAttribute("ois");
        if (ois == null) {
            ois = new ArrayList<>();
            req.getSession().setAttribute("ois", ois);
        }
        boolean found = false;
        for (OrderItem orderItem : ois) {
            if (orderItem.getProduct().getId() == oi.getProduct().getId()) {
                orderItem.setNum(orderItem.getNum() + oi.getNum());
                found = true;
                break;
            }
        }
        if (!found) {
            ois.add(oi);
        }
        resp.sendRedirect("listOrderItem");
    }
}
