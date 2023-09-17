package com.gaogzhen.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gaogzhen.dto.LoginFormDTO;
import com.gaogzhen.dto.Result;
import com.gaogzhen.entity.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
public interface IUserService extends IService<User> {

    /**
     * 发送验证码
     * @param phone
     * @return 通用返回
     */
    Result sendCode(String phone);

    /**
     * 登录
     * @param loginForm 登录表单
     * @return 通用返回
     */
    Result login(LoginFormDTO loginForm);
}
