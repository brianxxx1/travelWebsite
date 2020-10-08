package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Route;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IRouteDao {
    List<Route> findByCidNPage(@Param("cid") Integer cid, @Param("start") Integer start, @Param("pageSize") Integer pageSize,@Param("rname") String rname);
    Integer totalEntries (@Param("cid")Integer cid, @Param("rname")String rname);
}
