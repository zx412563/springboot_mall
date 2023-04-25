package com.jim.springboot_mall.dao;

import com.jim.springboot_mall.dto.UserRegisterRequest;
import com.jim.springboot_mall.model.User;

public interface UserDao {
    Integer createUser(UserRegisterRequest userRegisterRequest);

    User getUserById(Integer userId);
}
