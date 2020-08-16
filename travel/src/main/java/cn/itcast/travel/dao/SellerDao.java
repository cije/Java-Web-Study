package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Seller;

public interface SellerDao {
    /**
     * 根据rid查询seller
     * @param sid
     * @return
     */
    Seller findById(int sid);
}
