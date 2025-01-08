package com.shihuanx.diary.controller;

import com.shihuanx.diary.entity.User;
import com.shihuanx.diary.response.Result;
import com.shihuanx.diary.service.LoginService;
import com.shihuanx.diary.utils.Jwt;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/diary/login")
@Tag(name = "登录控制类",description = "提供登录接口")
public class LoginController {

    private static final Logger logger = Logger.getLogger(LoginController.class.getName());

    @Autowired
    private LoginService loginService;

    @PostMapping
    @Operation(summary = "一键登录注册接口",description = "以json格式传入手机号和密码")
    public Result login(@RequestBody User user) {
        logger.log(Level.INFO,"{0}正在登录",new Object[]{user});
        User u=loginService.login(user);
        return u!=null?Result.success(Jwt.getToken(u)):Result.error("手机号或密码错误");
    }

}
