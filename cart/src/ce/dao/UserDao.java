package ce.dao;

import ce.bean.User;
import cn.hutool.db.Db;
import cn.hutool.db.Entity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    public User getUser(String name, String password) {
        User user = new User();
        try {
            Entity where = Entity.create("user").set("name", name).set("password", password);
            user = Db.use().find(where, User.class).get(0);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }
}
