package cn.itcast.travel.dao;

import cn.itcast.travel.domain.User;

public interface UserDao {
    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * 用户保存添加
     * @param user
     * @return
     */
    void save(User user);

    /**
     * 通过激活码查找user
     * @param code
     * @return
     */
    User findByCode(String code);

    /**
     * 更改用户激活状态
     * @param user
     */
    void updateUserStatus(User user);
}
