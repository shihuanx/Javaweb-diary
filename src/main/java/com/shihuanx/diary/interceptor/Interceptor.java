package com.shihuanx.diary.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.shihuanx.diary.entity.User;
import com.shihuanx.diary.exception.JwtErrorException;
import com.shihuanx.diary.response.Result;
import com.shihuanx.diary.utils.Jwt;
import com.shihuanx.diary.utils.UserHolder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.logging.Level;
import java.util.logging.Logger;

import static com.shihuanx.diary.utils.Jwt.decodeUser;

@Component
public class Interceptor implements HandlerInterceptor {

    private static final Logger logger = Logger.getLogger(Interceptor.class.getName());

    @Override//目标资源方法运行前运行,返回true;放行,返回false
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String url = request.getRequestURI();
        logger.log(Level.INFO,"请求的url为：{0}",new Object[]{url});

        String token = request.getHeader("token");

        if (!StringUtils.hasLength(token)){
            logger.info("请求头token为空，返回未登录信息");
            Result error= Result.error("NOT_LOGIN");

            String notLogin = JSONObject.toJSONString(error);
            response.getWriter().write(notLogin);
            return false;
        }

        try {
            Jwt.verify(token);
        }catch (JwtErrorException e){
            e.printStackTrace();
            logger.info("解析令牌失败,返回未登录错误信息");
            Result error = Result.error("NOT_LOGIN");

            String notLogin = JSONObject.toJSONString(error);
            response.getWriter().write(notLogin);
            return false;
        }

        logger.info("令牌合法,放行");
        User user = decodeUser(token);
        UserHolder.setUser(user);//设置线程局部变量user
        return true;
    }

    @Override//目标资源方法运行后运行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        UserHolder.removeUser();//释放线程局部变量user
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }
}