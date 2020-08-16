package servletDemo;

import javax.imageio.ImageIO;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet("/checkCodeServlet")
public class CheckCodeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int width = 100, height = 50;
        //1.创建一对象，在内存中图片（验证码图片对象）
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        //2.美化图片
        //2.1 填充背景色
        Graphics g = image.getGraphics();
        g.setColor(Color.pink);
        g.fillRect(0, 0, width - 1, height - 1);

        //2.2画边框
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, width, height);

        String str = "ABCDEFGHIGKLMNOPQRSTUVWXYZa0123456789bcdefghigklmnopqrstuvwxyz";
        //生成随机角标
        Random random = new Random();
        StringBuilder stb = new StringBuilder();
        for (int i = 1; i <= 4; i++) {
            int index = random.nextInt(str.length());
            //获取字符
            char ch = str.charAt(index);
            stb.append(ch);
            //2.3写验证码
            g.drawString(ch + "", width * i / 5, height / 2);
        }
        String checkCode_session = stb.toString();
        //将验证码存入session
        req.getSession().setAttribute("checkCode_session", checkCode_session);
        //2.4画干扰线
        int x1, x2, y1, y2;
        g.setColor(Color.GREEN);
        for (int i = 0; i < 6; i++) {
            //随机生成坐标点
            x1 = random.nextInt(width);
            x2 = random.nextInt(width);
            y1 = random.nextInt(height);
            y2 = random.nextInt(height);
            g.drawLine(x1, y1, x2, y2);
        }

        //3.将图片输出到页面展示
        ImageIO.write(image, "jpg", resp.getOutputStream());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
