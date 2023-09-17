package com.gaogzhen.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gaogzhen.dto.Result;
import com.gaogzhen.entity.User;
import com.gaogzhen.mapper.UserMapper;
import com.gaogzhen.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author gaogzhen
 * @since 2023-09-17
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public Result sendCode(String phone) {
        // 1. 校验手机号
        // 2.
        return Result.ok();
    }
}
