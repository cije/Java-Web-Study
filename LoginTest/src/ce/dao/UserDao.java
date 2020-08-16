package ce.dao;

import ce.domain.User;
import ce.utils.JDBCUtil;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 操作数据库中user表的类
 */
public class UserDao {
    /**
     * 生命JDBCTemplate对象共用
     */
    private JdbcTemplate template = new JdbcTemplate(JDBCUtil.getDataSourde());

    /**
     * 登陆方法
     *
     * @param loginUser 只有用户名和密码
     * @return user包含用户所有数据
     */
    public User login(User loginUser) {
        try {
            //1.编写sql
            String sql = "select * from user where username=? and password=?";
            //2.调用query方法
            User user = template.queryForObject(sql,
                    new BeanPropertyRowMapper<User>(User.class),
                    loginUser.getUsername(), loginUser.getPassword());
            return user;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
