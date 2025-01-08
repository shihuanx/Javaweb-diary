package com.shihuanx.diary.service.impl;

import com.shihuanx.diary.entity.User;
import com.shihuanx.diary.mapper.LoginMapper;
import com.shihuanx.diary.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginMapper loginMapper;

    //一键登录注册 先查询手机号 如果手机号都没有则进行注册操作
    //如果存在手机号 则继续比对密码 如果都正确则登录 反之返回手机号或密码错误信息
    @Override
    public User login(User user) {
        if(loginMapper.getUserByPhoneNumber(user)==null){
            loginMapper.addUser(user);
            return user;
        }else{
            return loginMapper.getUserByPhoneNumberAndPassword(user);
        }
    }
}
