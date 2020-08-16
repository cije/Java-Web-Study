package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.FavDao;
import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.dao.RouteImgDao;
import cn.itcast.travel.dao.SellerDao;
import cn.itcast.travel.dao.impl.FavDaoImpl;
import cn.itcast.travel.dao.impl.RouteDaoImpl;
import cn.itcast.travel.dao.impl.RouteImgDaoImpl;
import cn.itcast.travel.dao.impl.SellerDaoImpl;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.RouteImg;
import cn.itcast.travel.domain.Seller;
import cn.itcast.travel.service.RouteService;

import java.util.List;

public class RouteServiceImpl implements RouteService {
    private RouteDao dao = new RouteDaoImpl();
    private RouteImgDao imgDao = new RouteImgDaoImpl();
    private SellerDao sellerDao = new SellerDaoImpl();
    private FavDao favDao = new FavDaoImpl();

    /**
     * 根据类别进行分页查询
     *
     * @param cid         类别id
     * @param currentPage 当前页码
     * @param pageSize    每页条数
     */
    @Override
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize, String rname) {
        PageBean<Route> pb = new PageBean<>();
        pb.setCurrentPage(currentPage);
        pb.setPageSize(pageSize);

        int totalCount = dao.findTotalCount(cid, rname);
        pb.setTotalCount(totalCount);

        int start = (currentPage - 1) * pageSize;
        List<Route> list = dao.findByPage(cid, start, pageSize, rname);
        pb.setList(list);

        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
        pb.setTotalPage(totalPage);
        return pb;
    }

    /**
     * 根据rid查询
     *
     * @param rid
     * @return
     */
    @Override
    public Route findOne(String rid) {
        Route route = dao.findOne(Integer.parseInt(rid));

        //查询图片集合
        List<RouteImg> imgs = imgDao.findByRid(route.getRid());
        route.setRouteImgList(imgs);

        //根据route的sid查询商家
        Seller seller = sellerDao.findById(route.getSid());
        route.setSeller(seller);
        //查询收藏次数
        int count = favDao.findCountByRid(route.getRid());
        route.setCount(count);
        return route;
    }
}
