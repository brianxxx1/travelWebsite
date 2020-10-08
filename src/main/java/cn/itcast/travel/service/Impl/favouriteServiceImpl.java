package cn.itcast.travel.service.Impl;

import cn.itcast.travel.dao.IFavouriteDao;
import cn.itcast.travel.service.IFavouriteService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

public class favouriteServiceImpl implements IFavouriteService {
    IFavouriteDao mapper;
    SqlSession sqlSession;
    @Override
    public Boolean isFavourite(String rid, Integer uid) throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("myBatis.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory build = builder.build(resourceAsStream);
        sqlSession = build.openSession();
        mapper = sqlSession.getMapper(IFavouriteDao.class);
        Boolean favourite = mapper.isFavourite(uid, rid);
        if (favourite!=null){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Boolean insertNewFav(Integer uid, String rid) throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("myBatis.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory build = builder.build(resourceAsStream);
        sqlSession = build.openSession();
        mapper = sqlSession.getMapper(IFavouriteDao.class);
        Boolean aBoolean = mapper.insertNewFav(uid, new Date(), rid);
        sqlSession.commit();
        return aBoolean;
    }
}
