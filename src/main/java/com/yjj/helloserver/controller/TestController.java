package com.yjj.helloserver.controller;

import com.yjj.helloserver.common.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

    // 接口地址：http://localhost:8080/api/test
    @GetMapping("/test")
    public Result<String> test() {
        return Result.success("测试接口访问成功！");
    }
}