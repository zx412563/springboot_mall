package com.jim.springboot_mall.service.impl;

import com.jim.springboot_mall.dao.UserDao;
import com.jim.springboot_mall.dto.UserRegisterRequest;
import com.jim.springboot_mall.model.User;
import com.jim.springboot_mall.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class UserServiceImpl implements UserService {

    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    UserDao userDao;

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        User user = userDao.getUserByEmail(userRegisterRequest.getEmail());

        //查詢 email 是否重複
        if (user != null) {
            log.warn("該email {} 已被註冊",userRegisterRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } else {
            //創建帳號
            return userDao.createUser(userRegisterRequest);
        }
    }

    @Override
    public User getUserById(Integer userId) {

        return userDao.getUserById(userId);
    }
}
