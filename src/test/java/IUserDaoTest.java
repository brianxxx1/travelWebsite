
import cn.itcast.travel.dao.IUserDao;
import cn.itcast.travel.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class IUserDaoTest {
    IUserDao mapper;
    SqlSession sqlSession;
    @Before
    public void before() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("myBatis.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory build = builder.build(resourceAsStream);
        sqlSession = build.openSession();
        mapper = sqlSession.getMapper(IUserDao.class);
    }
    @Test
    public void testFindAll(){
        List<User> all = mapper.findAll();
        for (User user : all) {
            System.out.println(user);
        }
    }
    @Test
    public void testFindByNameAndPassword(){
        User byUserNameAndPassword = mapper.findByUserNameAndPassword("xuwenshuo12312", "jicococ0319");
        System.out.println(byUserNameAndPassword);
    }
    @Test
    public void testUserInsert(){
        User user = new User(1,"jiayinliu","jicococ","Jiayin","2010-12-05","女","13944021392","xuw57@mcmaster",null,null);
        Boolean aBoolean = mapper.userInsert(user);
        System.out.println(aBoolean);
    }
    @Test
    public void testFindByUserName(){
        User xuwenshuo = mapper.findByUserName("xuwenshuo");
        System.out.println(xuwenshuo);
    }
    @After
    public void after(){
        sqlSession.commit();
        sqlSession.close();
    }

}
