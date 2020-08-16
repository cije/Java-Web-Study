package cn.itcast.travel.service;

import cn.itcast.travel.domain.User;

import java.util.Map;

public interface UserService {
    /**
     * 注册用户
     *
     * @param user
     * @return boolean
     */
    boolean regist(User user);

    /**
     * 激活用户
     *
     * @param code
     * @return
     */
    boolean active(String code);

    /**
     * 登录方法
     *
     * @param user
     * @return map<uid, flag>： uid: 用户id flag: 用户名找不到 1：正常登录 -1：未激活 -2：密码错误
     */
    int[] login(User user);
}
