import cn.itcast.travel.dao.IFavouriteDao;
import cn.itcast.travel.dao.IRouteDao;
import cn.itcast.travel.service.IFavouriteService;
import cn.itcast.travel.service.Impl.favouriteServiceImpl;
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

public class IFavouriteDaoTest {
    IFavouriteDao mapper;
    SqlSession sqlSession;
    @Before
    public void before() throws IOException {
        BasicConfigurator.configure ();
        InputStream resourceAsStream = Resources.getResourceAsStream("myBatis.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory build = builder.build(resourceAsStream);
        sqlSession = build.openSession();
        mapper = sqlSession.getMapper(IFavouriteDao.class);
    }
    @Test
    public void isFavouriteTest(){
        System.out.println(mapper.isFavourite(7,"2"));
    }
    @Test
    public void generalTest() throws IOException {
        IFavouriteService iFavouriteService = new favouriteServiceImpl();
        Boolean favourite = iFavouriteService.isFavourite("2", 8);
        System.out.println(favourite);
    }
    @Test
    public void insertNewFavTest() throws IOException {
        IFavouriteService iFavouriteService = new favouriteServiceImpl();
        Boolean aBoolean = iFavouriteService.insertNewFav(8, "5");
        System.out.println(aBoolean);
    }

    @After
    public void after(){
        sqlSession.commit();
        sqlSession.close();
    }
}
