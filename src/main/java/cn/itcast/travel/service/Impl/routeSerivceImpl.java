package cn.itcast.travel.service.Impl;

import cn.itcast.travel.dao.IRouteDao;
import cn.itcast.travel.dao.IRouteObjectDao;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.pageBean;
import cn.itcast.travel.service.IRouteService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class routeSerivceImpl implements IRouteService {
    @Override
    public pageBean<Route> pageQuery(Integer cid, Integer currentPage, Integer pageSize, String rname) throws IOException {
        pageBean<Route> pageBean = new pageBean<>();
        pageBean.setCurrentPage(currentPage);
        pageBean.setPageSize(pageSize);
        InputStream resourceAsStream = Resources.getResourceAsStream("myBatis.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory build = builder.build(resourceAsStream);
        SqlSession sqlSession = build.openSession();
        IRouteDao mapper = sqlSession.getMapper(IRouteDao.class);
        Integer totalEntries = mapper.totalEntries(cid,rname);
        pageBean.setTotalCount(totalEntries);
        int start = (currentPage-1)*pageSize;
        List<Route> list = mapper.findByCidNPage(cid, start, pageSize, rname);
        pageBean.setList(list);
        int totalPage = (totalEntries%pageSize == 0)?totalEntries/pageSize:(totalEntries/pageSize)+1;
        pageBean.setTotalPage(totalPage);
        return pageBean;
    }

    @Override
    public Route findOne(String rid) throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("myBatis.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory build = builder.build(resourceAsStream);
        SqlSession sqlSession = build.openSession();
        IRouteObjectDao mapper = sqlSession.getMapper(IRouteObjectDao.class);
        Integer favCount = mapper.findFavCount(rid);
        Route byRid = mapper.findByRid(rid);
        byRid.setCount(favCount);
        byRid.setRouteImgList(mapper.findImgs(rid));
        byRid.setSeller(mapper.findSeller(byRid.getSid()));
        return byRid;
    }
}
