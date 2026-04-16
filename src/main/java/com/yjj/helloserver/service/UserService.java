package com.yjj.helloserver.service;

import com.yjj.helloserver.common.Result;
import com.yjj.helloserver.dto.UserDTO;
import com.yjj.helloserver.entity.User;

public interface UserService {
    Result<String> register(UserDTO dto);
    Result<String> login(UserDTO dto);
    Result<User> getById(Long id);
}