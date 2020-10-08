package cn.itcast.travel.dao;

import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface IFavouriteDao {
    Boolean isFavourite(@Param("uid") Integer uid,@Param("rid") String rid);
    Boolean insertNewFav(@Param("uid") Integer uid, @Param("date") Date date, @Param("rid") String rid);
}
