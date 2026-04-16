package com.yjj.helloserver.controller;

import com.yjj.helloserver.common.Result;
import com.yjj.helloserver.dto.UserDTO;
import com.yjj.helloserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result<String> register(@RequestBody UserDTO dto) {
        return userService.register(dto);
    }

    @PostMapping("/login")
    public Result<String> login(@RequestBody UserDTO dto) {
        return userService.login(dto);
    }

    // 作业分页接口
    @GetMapping("/page")
    public Result<Object> getUserPage(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "5") Integer pageSize
    ) {
        return userService.getUserPage(pageNum, pageSize);
    }
}