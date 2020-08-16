package ce.dao.impl;

import ce.dao.UserDao;
import ce.domain.User;
import ce.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserDaoImpl implements UserDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<User> findAll() {
        String sql = "select * from user1";
        List<User> list = template.query(sql, new BeanPropertyRowMapper<User>(User.class));
        return list;
    }

    @Override
    public void addUser(User user) {
        String sql = "insert into user1 values(null,?,?,?,?,?,?)";
        template.update(sql, user.getName(), user.getGender(), user.getAge(), user.getAddress(), user.getQq(), user.getEmail());
    }

    @Override
    public void del(int id) {
        String sql = "delete from user1 where id=?";
        template.update(sql, id);
    }

    @Override
    public User findById(int id) {
        String sql = "select * from user1 where id=?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), id);
    }

    @Override
    public void update(User user) {
        String sql = "update user1 set name= ?,gender= ?,age= ?,address= ?,qq= ?,email= ? where id= ?";
        template.update(sql, user.getName(), user.getGender(), user.getAge(), user.getAddress(), user.getQq(), user.getEmail(), user.getId());
    }

    @Override
    public int findTotalCount(Map<String, String[]> condition) {
        String sql = "select count(*) from user1 where 1=1 ";
        StringBuilder stb = new StringBuilder(sql);
        //遍历map
        Set<Map.Entry<String, String[]>> entries = condition.entrySet();
        List<Object> params = new ArrayList<>();
        for (Map.Entry<String, String[]> entry : entries) {
            String value = entry.getValue()[0];
            String key = entry.getKey();
            if ("currentPage".equals(key) || "rows".equals(key)) {
                continue;
            }
            //判断value是否有值
            if (value != null && !"".equals(value)) {
                stb.append("and " + key + " like ? ");
                params.add("%" + value + "%");
            }
        }
        System.out.println(stb.toString());
        System.out.println(params);
        return template.queryForObject(stb.toString(), Integer.class, params.toArray());
    }

    @Override
    public List<User> findByPage(int start, int rows, Map<String, String[]> condition) {
        String sql = "select * from user1 where 1=1 ";
        StringBuilder stb = new StringBuilder(sql);
        //遍历map
        Set<Map.Entry<String, String[]>> entries = condition.entrySet();
        List<Object> params = new ArrayList<>();
        for (Map.Entry<String, String[]> entry : entries) {
            String value = entry.getValue()[0];
            String key = entry.getKey();
            if ("currentPage".equals(key) || "rows".equals(key)) {
                continue;
            }
            //判断value是否有值
            if (value != null && !"".equals(value)) {
                stb.append("and " + key + " like ? ");
                params.add("%" + value + "%");
            }
        }
        //添加分页的查询
        stb.append(" limit ?,? ");
        params.add(start);
        params.add(rows);
        System.out.println(stb.toString());
        System.out.println(params);
        return template.query(stb.toString(), new BeanPropertyRowMapper<User>(User.class), params.toArray());
    }
}
