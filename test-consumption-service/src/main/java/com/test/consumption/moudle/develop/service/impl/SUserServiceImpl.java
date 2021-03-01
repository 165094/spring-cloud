package com.test.consumption.moudle.develop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.test.consumption.moudle.develop.dao.SUserMapper;
import com.test.consumption.moudle.develop.entity.SUser;
import com.test.consumption.moudle.develop.service.ISUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class SUserServiceImpl extends ServiceImpl<SUserMapper, SUser> implements ISUserService {

    @Override
    public SUser selectUserByName(String name) {
        SUser sUser = this.baseMapper.selectUserByName(name);
        return sUser;
        
    }
}
