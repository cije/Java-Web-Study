package ce.dao;

import ce.domain.User;

import java.util.List;
import java.util.Map;

public interface UserDao {
    /**
     * 找到所有的user
     *
     * @return user集合
     */
    List<User> findAll();

    /**
     * 添加user
     *
     * @param user 添加的user
     */
    void addUser(User user);

    /**
     * 删除user
     *
     * @param id
     */
    void del(int id);

    /**
     *
     * @param id userid
     * @return user
     */
    User findById(int id);

    void update(User user);

    /**
     * 查询总记录数
     * @return 总记录数
     * @param condition
     */
    int findTotalCount(Map<String, String[]> condition);

    /**
     * 查询分页数据
     * @param start
     * @param rows
     * @param condition
     * @return
     */
    List<User> findByPage(int start, int rows, Map<String, String[]> condition);
}
