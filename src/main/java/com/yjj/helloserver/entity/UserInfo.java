package com.yjj.helloserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user_info")
public class UserInfo {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String realName;
    private String phone;
    private String address;
    // 重点：全部改为Long类型，和接口Long入参完全匹配
    private Long userId;
}