package com.example.springbootmybatis.service.impl;

import com.example.springbootmybatis.bean.Module;
import com.example.springbootmybatis.bean.User;
import com.example.springbootmybatis.dao.UserMapper;
import com.example.springbootmybatis.service.UserService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Resource
    private  UserMapper userMapper;


    @Override
    public void addUser(User user) {
        userMapper.insert(user);
    }

    @Override
    public List<User> queryAllUser(int pageNum, int pageSize) {
        return userMapper.queryAllUser();
    }

    @Override
    public List<Module> getPrivilegeListByUser(User user) {
        return userMapper.getPrivilegeListByUser(user);
    }

    @Override
    public User queryUserByUserName(String key) {
        return userMapper.queryUserByUserName(key);
    }
}
