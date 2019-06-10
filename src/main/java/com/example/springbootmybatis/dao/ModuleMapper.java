package com.example.springbootmybatis.dao;

import com.example.springbootmybatis.bean.Module;

public interface ModuleMapper {
    int deleteByPrimaryKey(Integer mId);

    int insert(Module record);

    int insertSelective(Module record);

    Module selectByPrimaryKey(Integer mId);

    int updateByPrimaryKeySelective(Module record);

    int updateByPrimaryKey(Module record);
}