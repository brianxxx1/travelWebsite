package cn.itcast.travel.service;

import cn.itcast.travel.domain.User;

import java.io.IOException;

public interface IUserService {
    boolean userRegister(User user) throws IOException;
    User userLogin(User user) throws IOException;
}
