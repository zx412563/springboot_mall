package com.jim.springboot_mall.dao.impl;

import com.jim.springboot_mall.dao.UserDao;
import com.jim.springboot_mall.dto.UserRegisterRequest;
import com.jim.springboot_mall.model.User;
import com.jim.springboot_mall.rowmapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserDaoImpl implements UserDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Integer createUser(UserRegisterRequest userRegisterRequest) {
        String sql = "INSERT INTO user (email ,password ,created_date ,last_modified_date)" +
                " VALUES(:email ,:password ,:createdDate ,:lastModifiedDate)";
        Map<String, Object> map = new HashMap<>();

        Date now = new Date();

        map.put("email", userRegisterRequest.getEmail());
        map.put("password", userRegisterRequest.getPassword());
        map.put("createdDate", now);
        map.put("lastModifiedDate", now);
        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);
        //接mysql自動生成userId
        int userId = keyHolder.getKey().intValue();

        return userId;
    }


    @Override
    public User getUserById(Integer userId) {
        String sql = "SELECT user_id, email , password , created_date , last_modified_date" +
                " FROM user WHERE user_id = :userId ";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        List<User> userList = namedParameterJdbcTemplate.query(sql, map, new UserRowMapper());
        if (userList.size() > 0) {
            return userList.get(0);
        } else {
            return null;
        }
    }


    @Override
    public User getUserByEmail(String email) {
        String sql = "SELECT user_id , email , password ,created_date, last_modified_date " +
                "FROM user WHERE email = :email";
        Map<String, Object> map = new HashMap<>();
        map.put("email", email);
        List<User> userList = namedParameterJdbcTemplate.query(sql, map, new UserRowMapper());

        if (userList.size() > 0) {
            return userList.get(0);
        } else {
            return null;
        }
    }





}
