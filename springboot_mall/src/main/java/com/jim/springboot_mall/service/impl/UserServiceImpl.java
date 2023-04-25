package com.jim.springboot_mall.service.impl;

import com.jim.springboot_mall.dao.UserDao;
import com.jim.springboot_mall.dto.UserRegisterRequest;
import com.jim.springboot_mall.model.User;
import com.jim.springboot_mall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        return userDao.createUser(userRegisterRequest);
    }

    @Override
    public User getUserById(Integer userId) {

        return userDao.getUserById(userId);
    }
}
