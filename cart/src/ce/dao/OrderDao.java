package ce.dao;

import ce.bean.Order;
import ce.bean.User;
import cn.hutool.db.Db;
import cn.hutool.db.Entity;

import java.sql.SQLException;

public class OrderDao {
    public void insert(Order order) {
        Entity where = Entity.create("order_").set("uid", order.getUser().getId());
        try {
            int id=Db.use().insertForGeneratedKey(where).intValue();
            order.setId(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void main(String[] args) {
        OrderDao od=new OrderDao();
        Order order=new Order();
        User user=new User();
        user.setId(1);
        user.setName("tom");
        user.setPassword("123");
        order.setUser(user);
        od.insert(order);
        System.out.println(order.getId());
    }
}
