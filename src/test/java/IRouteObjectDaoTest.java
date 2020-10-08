import cn.itcast.travel.dao.IRouteDao;
import cn.itcast.travel.dao.IRouteObjectDao;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.RouteImg;
import cn.itcast.travel.domain.Seller;
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
import java.util.Date;
import java.util.List;

public class IRouteObjectDaoTest {
    IRouteObjectDao mapper;
    SqlSession sqlSession;
    @Before
    public void before() throws IOException {
        BasicConfigurator.configure ();
        InputStream resourceAsStream = Resources.getResourceAsStream("myBatis.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory build = builder.build(resourceAsStream);
        sqlSession = build.openSession();
        mapper = sqlSession.getMapper(IRouteObjectDao.class);
    }
    @Test
    public void findByRidTest(){
        Route byRid = mapper.findByRid("1");
        System.out.println(byRid);
    }

    @Test
    public void findSellerTest(){
        Seller seller = mapper.findSeller(1);
        System.out.println(seller);
    }
    @Test
    public void findImgsTest(){
        List<RouteImg> imgs = mapper.findImgs("1");
        for (RouteImg img : imgs) {
            System.out.println(img);
        }
    }
    @Test
    public void favCountTest(){
        Integer favCount = mapper.findFavCount("1");
        System.out.println(favCount);
        System.out.println(new Date());
    }
    @Test
    public void generalTest() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("myBatis.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory build = builder.build(resourceAsStream);
        SqlSession sqlSession = build.openSession();
        IRouteObjectDao mapper = sqlSession.getMapper(IRouteObjectDao.class);
        Route byRid = mapper.findByRid("1");
        byRid.setRouteImgList(mapper.findImgs("1"));
        byRid.setSeller(mapper.findSeller(byRid.getSid()));

        System.out.println(byRid);
    }
    @After
    public void after(){
        sqlSession.commit();
        sqlSession.close();
    }
}
