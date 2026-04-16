package com.yjj.helloserver.service.impl;

<<<<<<< HEAD
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yjj.helloserver.common.Result;
import com.yjj.helloserver.common.ResultCode;
import com.yjj.helloserver.dto.UserDTO;
import com.yjj.helloserver.entity.User;
import com.yjj.helloserver.mapper.UserMapper;
import com.yjj.helloserver.service.UserService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
=======
import com.yjj.helloserver.common.Result;
import com.yjj.helloserver.dto.UserDTO;
import com.yjj.helloserver.service.UserService;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
>>>>>>> origin/master

@Service
public class UserServiceImpl implements UserService {

<<<<<<< HEAD
    @Resource
    private UserMapper userMapper;

    @Override
    public Result<String> register(UserDTO dto) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, dto.getUsername());
        User exist = userMapper.selectOne(wrapper);

        if (exist != null) {
            return Result.error(ResultCode.USER_EXIST);
        }

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        userMapper.insert(user);

=======
    // 内存存储，不用数据库
    private static final Map<String, String> userMap = new HashMap<>();

    @Override
    public Result<String> register(UserDTO dto) {
        if (userMap.containsKey(dto.getUsername())) {
            return Result.error("用户名已存在");
        }
        userMap.put(dto.getUsername(), dto.getPassword());
>>>>>>> origin/master
        return Result.success("注册成功");
    }

    @Override
    public Result<String> login(UserDTO dto) {
<<<<<<< HEAD
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, dto.getUsername());
        User user = userMapper.selectOne(wrapper);

        if (user == null) {
            return Result.error(ResultCode.USER_NOT_EXIST);
        }
        if (!user.getPassword().equals(dto.getPassword())) {
            return Result.error(ResultCode.PASSWORD_ERROR);
        }
        return Result.success("登录成功");
    }

    @Override
    public Result<User> getById(Long id) {
        User user = userMapper.selectById(id);
        if (user == null) {
            return Result.error(ResultCode.USER_NOT_EXIST);
        }
        return Result.success(user);
    }
=======
        if (!userMap.containsKey(dto.getUsername())) {
            return Result.error("用户不存在");
        }
        String pwd = userMap.get(dto.getUsername());
        if (!pwd.equals(dto.getPassword())) {
            return Result.error("密码错误");
        }
        return Result.success("登录成功");
    }
>>>>>>> origin/master
}