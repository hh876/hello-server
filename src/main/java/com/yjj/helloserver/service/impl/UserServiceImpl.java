package com.yjj.helloserver.service.impl;

import com.yjj.helloserver.common.Result;
import com.yjj.helloserver.dto.UserDTO;
import com.yjj.helloserver.service.UserService;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    // 内存存储，不用数据库
    private static final Map<String, String> userMap = new HashMap<>();

    @Override
    public Result<String> register(UserDTO dto) {
        if (userMap.containsKey(dto.getUsername())) {
            return Result.error("用户名已存在");
        }
        userMap.put(dto.getUsername(), dto.getPassword());
        return Result.success("注册成功");
    }

    @Override
    public Result<String> login(UserDTO dto) {
        if (!userMap.containsKey(dto.getUsername())) {
            return Result.error("用户不存在");
        }
        String pwd = userMap.get(dto.getUsername());
        if (!pwd.equals(dto.getPassword())) {
            return Result.error("密码错误");
        }
        return Result.success("登录成功");
    }
}