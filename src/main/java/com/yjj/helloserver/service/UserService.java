package com.yjj.helloserver.service;

import com.yjj.helloserver.common.Result;
import com.yjj.helloserver.dto.UserDTO;
<<<<<<< HEAD
import com.yjj.helloserver.entity.User;
=======
>>>>>>> origin/master

public interface UserService {
    Result<String> register(UserDTO dto);
    Result<String> login(UserDTO dto);
<<<<<<< HEAD
    Result<User> getById(Long id);
=======
>>>>>>> origin/master
}