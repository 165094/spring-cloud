package com.test.consumption.moudle.develop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.test.consumption.moudle.develop.entity.SUser;

public interface ISUserService extends IService<SUser> {

    SUser selectUserByName(String name);
}
