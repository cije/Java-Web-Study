package ce.servlet;

import cn.hutool.db.Db;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class InitServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        Db db = Db.use();
    }
}
