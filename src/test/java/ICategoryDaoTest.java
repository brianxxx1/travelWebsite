import cn.itcast.travel.dao.ICategoryDao;
import cn.itcast.travel.dao.IUserDao;
import cn.itcast.travel.domain.Category;
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

public class ICategoryDaoTest {
    ICategoryDao mapper;
    SqlSession sqlSession;
    @Before
    public void before() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("myBatis.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory build = builder.build(resourceAsStream);
        sqlSession = build.openSession();
        mapper = sqlSession.getMapper(ICategoryDao.class);
    }
    @Test
    public void testFindAll(){
        List<Category> all = mapper.findAll();
        for (Category category : all) {
            System.out.println(category);
        }
    }
    @After
    public void after(){
        sqlSession.commit();
        sqlSession.close();
    }
}
