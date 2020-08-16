package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.util.MailUtils;
import cn.itcast.travel.util.UuidUtil;

import java.util.HashMap;
import java.util.Map;

public class UserServiceImpl implements UserService {
    private UserDao dao = new UserDaoImpl();

    /**
     * 注册用户
     *
     * @return
     */
    @Override
    public boolean regist(User user) {
        //1.根据用户名查询用户信息
        User u = dao.findByUsername(user.getUsername());

        if (u != null) {
            return false;
        }
        //2.保存用户信息
        //2.1设置激活码
        user.setCode(UuidUtil.getUuid());
        //2.2设置激活状态
        user.setStatus("N");
        //3.激活邮件发送
        String content = "<a href='http://localhost/travel/user/active?code=" + user.getCode() + "'>点击激活【黑马旅游网】</a>";
        try {
            MailUtils.sendMail(user.getEmail(), content, "激活邮件");
            dao.save(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 激活用户
     *
     * @param code
     * @return
     */
    @Override
    public boolean active(String code) {
        //1.根据激活码查询用户
        User user = dao.findByCode(code);
        if (user != null) {
            dao.updateUserStatus(user);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 登录方法
     *
     * @param user
     * @return
     */
    @Override
    public int[] login(User user) {
        String fStatus = "N";
        User u = dao.findByUsername(user.getUsername());
        int uid = 0, flag = 0;
        int[] res = new int[2];
        if (u == null) {
            //用户名找不到
        } else if (fStatus.equals(u.getStatus())) {
            //未激活
            flag = -1;
        } else if (!u.getPassword().equals(user.getPassword())) {
            //密码错误
            flag = -2;
        } else {
            //正常
            flag = 1;
            uid = u.getUid();
        }
        res[0] = uid;
        res[1] = flag;
        return res;
    }
}
