package com.example.springbootmybatis.service;

import com.example.springbootmybatis.bean.Module;
import com.example.springbootmybatis.bean.User;

import java.util.List;

public interface UserService {

    void addUser(User user);

    public List<User> queryAllUser(int pageNum,int pageSize);

    List<Module> getPrivilegeListByUser(User user);

    User queryUserByUserName(String key);
}
