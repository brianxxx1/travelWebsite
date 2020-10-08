package cn.itcast.travel.service;

import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.pageBean;

import java.io.IOException;

public interface IRouteService {
    pageBean<Route> pageQuery(Integer cid, Integer currentPage, Integer pageSize, String rname) throws IOException;
    Route findOne (String rid) throws IOException;
}
