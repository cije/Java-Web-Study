package ce.test;

import ce.dao.UserDao;
import ce.domain.User;
import org.junit.Test;

public class UserDaoTest {
    @Test
    public void testLogin() {
        User loginUser = new User();
        loginUser.setUsername("admin");
        loginUser.setPassword("admin");
        UserDao userDao = new UserDao();
        User user = userDao.login(loginUser);
        System.out.println(user);
    }
}
