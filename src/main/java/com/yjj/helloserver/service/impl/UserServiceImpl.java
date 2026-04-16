package com.yjj.helloserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yjj.helloserver.common.Result;
import com.yjj.helloserver.common.ResultCode;
import com.yjj.helloserver.dto.UserDTO;
import com.yjj.helloserver.entity.User;
import com.yjj.helloserver.mapper.UserMapper;
import com.yjj.helloserver.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public Result<String> register(UserDTO dto) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, dto.getUsername());
        User existUser = this.getOne(wrapper);

        if (existUser != null) {
            // 只传1个参数：ResultCode枚举，完全符合你方法定义
            return Result.error(ResultCode.USER_EXIST);
        }

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(DigestUtils.md5Hex(dto.getPassword()));
        this.save(user);
        return Result.success("注册成功");
    }

    @Override
    public Result<String> login(UserDTO dto) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, dto.getUsername());
        User user = this.getOne(wrapper);

        if (user == null) {
            // 只传1个参数！彻底删掉所有 resultCode:、数字、逗号、多余文字
            return Result.error(ResultCode.USER_NOT_EXIST);
        }

        String encodePwd = DigestUtils.md5Hex(dto.getPassword());
        if (!user.getPassword().equals(encodePwd)) {
            // 只传1个参数！彻底删掉所有 resultCode:、数字、逗号、多余文字
            return Result.error(ResultCode.PASSWORD_ERROR);
        }

        return Result.success("登录成功");
    }

    @Override
    public Result<Object> getUserPage(Integer pageNum, Integer pageSize) {
        Page<User> pageParam = new Page<>(pageNum, pageSize);
        this.page(pageParam, null);
        return Result.success(pageParam);
    }
}