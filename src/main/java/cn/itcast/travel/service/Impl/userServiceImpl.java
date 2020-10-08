package cn.itcast.travel.service.Impl;

import cn.itcast.travel.dao.IUserDao;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.IUserService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class userServiceImpl implements IUserService {
    IUserDao mapper;
    SqlSession sqlSession;

    @Override
    public boolean userRegister(User user) throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("myBatis.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory build = builder.build(resourceAsStream);
        sqlSession = build.openSession();
        mapper = sqlSession.getMapper(IUserDao.class);
        User byUserName = mapper.findByUserName(user.getUsername());
        if (byUserName==null){
            Boolean aBoolean = mapper.userInsert(user);
            sqlSession.commit();
            return aBoolean;
        }else{
            return false;
        }
    }

    @Override
    public User userLogin(User user) throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("myBatis.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory build = builder.build(resourceAsStream);
        sqlSession = build.openSession();
        mapper = sqlSession.getMapper(IUserDao.class);
        User byUserNameAndPassword = mapper.findByUserNameAndPassword(user.getUsername(), user.getPassword());
        return byUserNameAndPassword;
    }
}
