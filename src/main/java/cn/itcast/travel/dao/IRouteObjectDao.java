package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.RouteImg;
import cn.itcast.travel.domain.Seller;

import java.util.List;

public interface IRouteObjectDao {
    Route findByRid(String rid);
    Seller findSeller(Integer sid);
    List<RouteImg> findImgs(String rid);
    Integer findFavCount(String rid);
}
