package ce.servlet;

import ce.bean.Product;
import ce.dao.ProductDao;
import cn.hutool.db.Entity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductListServlet",urlPatterns = "/listProduct")
public class ProductListServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products=new ProductDao().list();
        req.setAttribute("products",products);
        req.getRequestDispatcher("listProduct.jsp").forward(req,resp);
    }
}
