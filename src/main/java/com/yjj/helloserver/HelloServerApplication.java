// 错误写法
// package com.example.helloserver;

// 正确写法（和你的目录结构完全对应）
package com.yjj.helloserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(HelloServerApplication.class, args);
    }
}