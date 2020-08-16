package ce.dao;

import ce.bean.OrderItem;
import cn.hutool.db.Db;
import cn.hutool.db.Entity;

import java.sql.SQLException;


public class OrderItemDao {
    public void insert(OrderItem oi) {
        Entity where = Entity.create("orderitem").set("pid", oi.getProduct().getId()).set("num", oi.getNum()).set("oid", oi.getOrder().getId());
        try {
            Db.use().insert(where);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
