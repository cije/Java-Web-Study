package ce.service;

import ce.domain.PageBean;
import ce.domain.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    /**
     * 查询所有的用户信息
     *
     * @return user集合
     */
    List<User> findAll();

    /**
     * 添加user
     * @param user 添加的user
     */
    void addUser(User user);

    /**
     * 根据id删除user
     * @param id user id
     */
    void delUser(String id);

    /**
     * 通过id查找user
     * @param id user id
     * @return user
     */
    User findUserById(String id);

    /**
     * 修改用户信息
     * @param user user
     */
    void updateUser(User user);

    /**
     * 批量删除用户
     * @param ids id
     */
    void delSelectedUser(String[] ids);

    /**
     * 分页条件查询
     * @param currentPage
     * @param rows
     * @param condition
     * @return
     */
    PageBean<User> findUserByPage(String currentPage, String rows, Map<String, String[]> condition);
}
