package com.yjj.helloserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yjj.helloserver.dto.UserDTO;
import com.yjj.helloserver.entity.User;
import com.yjj.helloserver.common.Result;

public interface UserService extends IService<User> {

    // 注册方法
    Result<String> register(UserDTO dto);

    // 登录方法
    Result<String> login(UserDTO dto);

    // 新增：分页查询用户列表
    Result<Object> getUserPage(Integer pageNum, Integer pageSize);
}