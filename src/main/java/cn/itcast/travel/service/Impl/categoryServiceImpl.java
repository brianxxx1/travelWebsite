package cn.itcast.travel.service.Impl;

import cn.itcast.travel.dao.ICategoryDao;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.ICategoryService;
import cn.itcast.travel.util.JedisUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class categoryServiceImpl implements ICategoryService {
    @Override
    public List<Category> findAll() throws IOException {
        Jedis jedis = JedisUtil.getJedis();

        Set<Tuple> categorys = jedis.zrangeWithScores("category", 0, -1);
        List<Category> all = null;
        if (categorys ==null || categorys.size() == 0){
            InputStream resourceAsStream = Resources.getResourceAsStream("myBatis.xml");
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            SqlSessionFactory build = builder.build(resourceAsStream);
            SqlSession sqlSession = build.openSession();
            ICategoryDao mapper = sqlSession.getMapper(ICategoryDao.class);
            all = mapper.findAll();
            for (int i = 0; i < all.size(); i++) {
                jedis.zadd("category",all.get(i).getCid(),all.get(i).getCname());
            }
        }
        else{
            all = new ArrayList<>();
            for (Tuple category : categorys) {
                Category category1 = new Category();
                category1.setCname(category.getElement());
                category1.setCid((int)category.getScore());
                all.add(category1);
            }
        }

        return all;
    }
}
