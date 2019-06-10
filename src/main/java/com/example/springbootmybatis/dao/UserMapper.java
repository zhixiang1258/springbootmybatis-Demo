package com.example.springbootmybatis.dao;

import com.example.springbootmybatis.bean.Module;
import com.example.springbootmybatis.bean.User;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> queryAllUser();

    List<Module> getPrivilegeListByUser(User user);

    User queryUserByUserName(String key);
}