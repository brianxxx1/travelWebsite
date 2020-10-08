package cn.itcast.travel.service;

import java.io.IOException;

public interface IFavouriteService {
    Boolean isFavourite(String rid, Integer uid) throws IOException;

    Boolean insertNewFav(Integer uid, String rid) throws IOException;
}
