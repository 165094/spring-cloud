package com.test.consumption.moudle.develop.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.test.consumption.moudle.develop.entity.SUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SUserMapper extends BaseMapper<SUser> {

    /**
     * 根据用户名查询用户信息
     * @param userName
     * @return
     */
    SUser selectUserByName(@Param(value = "userName") String userName);
}
