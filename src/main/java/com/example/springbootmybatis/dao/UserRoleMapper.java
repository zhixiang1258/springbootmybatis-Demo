package com.example.springbootmybatis.dao;

import com.example.springbootmybatis.bean.UserRole;

public interface UserRoleMapper {
    int insert(UserRole record);

    int insertSelective(UserRole record);
}