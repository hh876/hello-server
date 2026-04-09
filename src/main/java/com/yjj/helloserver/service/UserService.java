package com.yjj.helloserver.service;

import com.yjj.helloserver.common.Result;
import com.yjj.helloserver.dto.UserDTO;

public interface UserService {
    Result<String> register(UserDTO dto);
    Result<String> login(UserDTO dto);
}