package com.yjj.helloserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yjj.helloserver.common.Result;
import com.yjj.helloserver.dto.UserDTO;
import com.yjj.helloserver.entity.User;
import com.yjj.helloserver.entity.UserInfo;
import com.yjj.helloserver.vo.UserDetailVO;

public interface UserService extends IService<User> {
    // 原有功能
    Result<String> register(UserDTO dto);
    Result<String> login(UserDTO dto);
    Result<Object> getUserPage(Integer pageNum, Integer pageSize);

    // 任务7新增接口
    Result<UserDetailVO> getUserDetail(Long userId);
    Result<String> updateUserInfo(UserInfo userInfo);
    Result<String> deleteUser(Long userId);
}