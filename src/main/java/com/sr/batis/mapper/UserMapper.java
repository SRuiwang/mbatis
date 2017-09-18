package com.sr.batis.mapper;

import com.sr.batis.po.User;

import java.util.Map;

public interface UserMapper {

    public User findUserById(Integer id);

    public void insertUser(User user);

    public User findUserByVo(User user);

    public User findUserByMap(Map<String,Object> map);

    public String findUserNameById(Integer id);

    public User findUserByNameAndSex(User user);
}