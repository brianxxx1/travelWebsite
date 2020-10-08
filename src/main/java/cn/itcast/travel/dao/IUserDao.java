package cn.itcast.travel.dao;

import cn.itcast.travel.domain.User;

import java.util.List;

public interface IUserDao {
    User findByUserName(String username);
    List<User> findAll();
    Boolean userInsert(User user);
    User findByUserNameAndPassword(String username, String password);
}
