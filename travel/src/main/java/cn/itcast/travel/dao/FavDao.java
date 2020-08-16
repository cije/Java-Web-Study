package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Favorite;

public interface FavDao {
    /**
     * 根据rid和uid查询favorite
     */
    Favorite findByRidAndUid(int rid, int uid);

    /**
     * 根据rid查询收藏次数
     */
    int findCountByRid(int rid);

    /**
     * 添加收藏
     */
    void add(int rid, int uid);
}
