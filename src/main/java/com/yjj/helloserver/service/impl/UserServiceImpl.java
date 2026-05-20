package com.yjj.helloserver.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yjj.helloserver.common.Result;
import com.yjj.helloserver.common.ResultCode;
import com.yjj.helloserver.dto.UserDTO;
import com.yjj.helloserver.entity.User;
import com.yjj.helloserver.entity.UserInfo;
import com.yjj.helloserver.mapper.UserInfoMapper;
import com.yjj.helloserver.mapper.UserMapper;
import com.yjj.helloserver.service.UserService;
import com.yjj.helloserver.vo.UserDetailVO;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private UserInfoMapper userInfoMapper;

    private static final String CACHE_KEY_PREFIX = "user:detail:";

    // 原有注册功能
    @Override
    public Result<String> register(UserDTO dto) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, dto.getUsername());
        User existUser = this.getOne(wrapper);

        if (existUser != null) {
            return Result.error(501, "用户名已存在");
        }

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(DigestUtils.md5Hex(dto.getPassword()));
        this.save(user);
        return Result.success("注册成功");
    }

    // 原有登录功能
    @Override
    public Result<String> login(UserDTO dto) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, dto.getUsername());
        User user = this.getOne(wrapper);

        if (user == null) {
            return Result.error(404, "用户不存在");
        }

        String encodePwd = DigestUtils.md5Hex(dto.getPassword());
        if (!user.getPassword().equals(encodePwd)) {
            return Result.error(401, "密码错误");
        }

        return Result.success("登录成功");
    }

    // 原有分页功能
    @Override
    public Result<Object> getUserPage(Integer pageNum, Integer pageSize) {
        Page<User> pageParam = new Page<>(pageNum, pageSize);
        this.page(pageParam, null);
        return Result.success(pageParam);
    }

    // 多表联查+Redis缓存查询（已修复Java8 isBlank兼容问题）
    @Override
    public Result<UserDetailVO> getUserDetail(Long userId) {
        String key = CACHE_KEY_PREFIX + userId;

        String json = redisTemplate.opsForValue().get(key);
        // Java8完美兼容写法，无版本报错
        if (json != null && !json.trim().isEmpty()) {
            try {
                UserDetailVO cacheVO = JSONUtil.toBean(json, UserDetailVO.class);
                return Result.success(cacheVO);
            } catch (Exception e) {
                redisTemplate.delete(key);
            }
        }

        UserDetailVO detail = userInfoMapper.getUserDetail(userId);
        if (detail == null) {
            return Result.error(ResultCode.USER_NOT_EXIST.getCode(), ResultCode.USER_NOT_EXIST.getMsg());
        }

        redisTemplate.opsForValue().set(
                key,
                JSONUtil.toJsonStr(detail),
                10,
                TimeUnit.MINUTES
        );

        return Result.success(detail);
    }

    // 更新用户信息+删除缓存
    @Override
    @Transactional
    public Result<String> updateUserInfo(UserInfo userInfo) {
        if (userInfo == null || userInfo.getUserId() == null) {
            return Result.error(500, "参数不合法");
        }
        userInfoMapper.updateById(userInfo);
        String key = CACHE_KEY_PREFIX + userInfo.getUserId();
        redisTemplate.delete(key);
        return Result.success("用户信息更新成功");
    }

    // 删除用户+删除缓存
    @Override
    @Transactional
    public Result<String> deleteUser(Long userId) {
        this.removeById(userId);
        String key = CACHE_KEY_PREFIX + userId;
        redisTemplate.delete(key);
        return Result.success("用户删除成功");
    }
}