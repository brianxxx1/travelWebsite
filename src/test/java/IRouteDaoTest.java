import cn.itcast.travel.dao.IRouteDao;
import cn.itcast.travel.dao.IUserDao;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.BasicConfigurator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class IRouteDaoTest {
    IRouteDao mapper;
    SqlSession sqlSession;
    @Before
    public void before() throws IOException {
        BasicConfigurator.configure ();
        InputStream resourceAsStream = Resources.getResourceAsStream("myBatis.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory build = builder.build(resourceAsStream);
        sqlSession = build.openSession();
        mapper = sqlSession.getMapper(IRouteDao.class);
    }
    @Test
    public void testFindAll(){
        List<Route> byCidNPage = mapper.findByCidNPage(5, 0, 5,"null");
        System.out.println(byCidNPage);
    }
    @Test
    public void testTotalEntries(){
        Integer integer = mapper.totalEntries(5,"999");
        System.out.println(integer);
    }
    @After
    public void after(){
        sqlSession.commit();
        sqlSession.close();
    }
}
