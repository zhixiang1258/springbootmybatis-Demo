package com.example.springbootmybatis.controller;

import com.example.springbootmybatis.bean.User;
import com.example.springbootmybatis.service.UserService;
import com.example.springbootmybatis.utils.AjaxResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/user/test")
public class TestController {
    @Resource
    private UserService userService;
    @Resource(name = "redisTemplate")
    private RedisTemplate redisTemplate;
    @Resource(name = "redisTemplate")
    private ValueOperations valueOperations;

    private Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    @ResponseBody
    @RequestMapping(value = "/addUser",method = RequestMethod.POST,produces = "application/json")
    public void addUser(@Valid @RequestBody User user){
        try {
            userService.addUser(user);
        } catch (Exception e) {
           LOGGER.info(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/queryAllUser/{pageNum}/{pageSize}",method = RequestMethod.GET)
    public String queryAllUser(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize){
        AjaxResponse ajaxResponse = new AjaxResponse();
        try {
            List<User> list = userService.queryAllUser(pageNum, pageSize);
            ajaxResponse.setCode(0);
            ajaxResponse.setMsg("seccess");
            ajaxResponse.setData(list);
            return ajaxResponse.toJSONString();
        }catch (Exception e){
            LOGGER.info(e.getMessage());
            ajaxResponse.setCode(0);
            ajaxResponse.setMsg("error");
            return ajaxResponse.toJSONString();
        }
    }

    @ResponseBody
    @RequestMapping(value = "/testRedis",method = RequestMethod.POST,produces = "application/json")
    public String testRedis(@Valid @RequestBody User user){
        AjaxResponse ajaxResponse = new AjaxResponse();
        try {
            //Redis以键值对的形式存储
            valueOperations.set("userName",user.getUserName());
            valueOperations.set("password",user.getPassword());
            valueOperations.set("phone",user.getPhone());
            //Redis存储对象

            //Redis存储List

            //Redis存储Set

            ajaxResponse.setCode(0);
            ajaxResponse.setMsg("seccess");
            return ajaxResponse.toJSONString();
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            ajaxResponse.setCode(0);
            ajaxResponse.setMsg("error");
            return ajaxResponse.toJSONString();
        }
    }
}
