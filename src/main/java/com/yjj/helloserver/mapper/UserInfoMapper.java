package com.yjj.helloserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yjj.helloserver.entity.UserInfo;
import com.yjj.helloserver.vo.UserDetailVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {

    @Select("SELECT u.id AS userId, u.username, i.real_name AS realName, i.phone, i.address FROM sys_user u LEFT JOIN user_info i ON u.id = i.user_id WHERE u.id = #{userId}")
    UserDetailVO getUserDetail(@Param("userId") Long userId);
}