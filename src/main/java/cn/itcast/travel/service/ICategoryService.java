package cn.itcast.travel.service;

import cn.itcast.travel.domain.Category;

import java.io.IOException;
import java.util.List;

public interface ICategoryService {
     List<Category> findAll () throws IOException;
}
