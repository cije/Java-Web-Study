package cn.itcast.travel.service;

public interface FavService {
    /**
     * 判断是否收藏
     */
    boolean isFav(String rid, int uid);

    /**
     * 添加收藏
     * @param rid
     * @param uid
     */
    void add(String rid, int uid);
}
