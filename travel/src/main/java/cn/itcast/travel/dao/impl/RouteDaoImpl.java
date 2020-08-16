package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class RouteDaoImpl implements RouteDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 根据cid查询总记录数
     */
    @Override
    public int findTotalCount(int cid, String rname) {
        String sql = "select count(*) from route where 1=1 ";
        StringBuilder stb = new StringBuilder(sql);
        List<Object> params = new ArrayList<>();
        if (cid != 0) {
            stb.append(" and cid=? ");
            params.add(cid);
        }
        if (rname != null && rname.length() > 0) {
            stb.append(" and rname like ?");
            params.add("%" + rname + "%");
        }
        sql = stb.toString();
        return template.queryForObject(sql, Integer.class, params.toArray());
    }

    /**
     * 根据cid，start，pageSize查询当前页的数据集合
     */
    @Override
    public List<Route> findByPage(int cid, int start, int pageSize, String rname) {
        String sql = "select * from route where 1=1 ";
        StringBuilder stb = new StringBuilder(sql);
        List<Object> params = new ArrayList<>();
        if (cid != 0) {
            stb.append(" and cid= ? ");
            params.add(cid);
        }
        if (rname != null && rname.length() > 0) {
            stb.append(" and rname like ? ");
            params.add("%" + rname + "%");
        }
        stb.append(" limit ? , ? ");
        params.add(start);
        params.add(pageSize);
        sql = stb.toString();
        return template.query(sql, new BeanPropertyRowMapper<>(Route.class), params.toArray());
    }

    /**
     * 根据rid查询
     *
     * @param rid
     * @return
     */
    @Override
    public Route findOne(int rid) {
        String sql = "select * from route where rid=?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<>(Route.class), rid);
    }
}
