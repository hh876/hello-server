package com.yjj.helloserver.entity; // 与目录路径完全一致

public class User {
    private Long id;
    private String name;
    private Integer age;

    // 无参构造
    public User() {}

    // 全参构造
    public User(Long id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    // Getter & Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }
} // 确保最后有闭合大括号，解决 "}" expected 错误