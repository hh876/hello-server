package com.yjj.helloserver.controller;

import com.yjj.helloserver.common.Result;
import com.yjj.helloserver.dto.UserDTO;
import com.yjj.helloserver.entity.UserInfo;
import com.yjj.helloserver.service.UserService;
import com.yjj.helloserver.vo.UserDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // 原有注册接口
    @PostMapping("/register")
    public Result<String> register(@RequestBody UserDTO dto) {
        return userService.register(dto);
    }

    // 原有登录接口
    @PostMapping("/login")
    public Result<String> login(@RequestBody UserDTO dto) {
        return userService.login(dto);
    }

    // 原有分页接口
    @GetMapping("/page")
    public Result<Object> getUserPage(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "5") Integer pageSize
    ) {
        return userService.getUserPage(pageNum, pageSize);
    }

    // 任务7：查询用户详情（多表联查+Redis缓存）
    @GetMapping("/{id}/detail")
    public Result<UserDetailVO> getUserDetail(@PathVariable("id") Long userId) {
        return userService.getUserDetail(userId);
    }

    // 任务7：更新用户扩展信息
    @PutMapping("/{id}/detail")
    public Result<String> updateUserInfo(@PathVariable("id") Long userId,
                                         @RequestBody UserInfo userInfo) {
        // 现在UserInfo.userId是Long类型，setUserId(Long)完美匹配，报错直接消失
        userInfo.setUserId(userId);
        return userService.updateUserInfo(userInfo);
    }

    // 任务7：删除用户
    @DeleteMapping("/{id}")
    public Result<String> deleteUser(@PathVariable("id") Long userId) {
        return userService.deleteUser(userId);
    }
}