package com.example.springbootmybatis.dao;

import com.example.springbootmybatis.bean.ModuleRole;

public interface ModuleRoleMapper {
    int insert(ModuleRole record);

    int insertSelective(ModuleRole record);
}