package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.FavDao;
import cn.itcast.travel.dao.impl.FavDaoImpl;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.service.FavService;

public class FavServiceImpl implements FavService {
    private FavDao dao = new FavDaoImpl();

    /**
     * 判断是否收藏
     *
     * @param rid
     * @param uid
     */
    @Override
    public boolean isFav(String rid, int uid) {
        Favorite favorite = dao.findByRidAndUid(Integer.parseInt(rid), uid);
        return favorite != null;
    }

    /**
     * 添加收藏
     *
     * @param rid
     * @param uid
     */
    @Override
    public void add(String rid, int uid) {
        dao.add(Integer.parseInt(rid), uid);
    }
}
