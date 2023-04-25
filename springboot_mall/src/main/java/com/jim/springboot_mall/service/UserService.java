package com.jim.springboot_mall.service;

import com.jim.springboot_mall.dto.UserRegisterRequest;
import com.jim.springboot_mall.model.User;

public interface UserService {

    User getUserById(Integer userId);
    Integer register(UserRegisterRequest userRegisterRequest);


}
