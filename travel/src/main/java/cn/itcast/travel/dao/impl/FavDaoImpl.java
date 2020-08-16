package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.FavDao;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;

public class FavDaoImpl implements FavDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 根据rid和uid查询favorite
     *
     * @param rid
     * @param uid
     */
    @Override
    public Favorite findByRidAndUid(int rid, int uid) {
        Favorite favorite = null;
        try {
            String sql = "Select * from  favorite where rid= ? and uid= ?";
            favorite = template.queryForObject(sql, new BeanPropertyRowMapper<>(Favorite.class), rid, uid);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return favorite;
    }

    /**
     * 根据rid查询收藏次数
     */
    @Override
    public int findCountByRid(int rid) {
        String sql = "Select count(*) from  favorite where rid= ?";
        return template.queryForObject(sql, Integer.class, rid);
    }

    /**
     * 添加收藏
     *
     * @param parseInt
     * @param uid
     */
    @Override
    public void add(int rid, int uid) {
        String sql = "insert into favorite values(?,?,?)";
        template.update(sql, rid, LocalDate.now(), uid);
    }
}
