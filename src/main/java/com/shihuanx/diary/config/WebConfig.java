package com.shihuanx.diary.config;

import com.shihuanx.diary.interceptor.Interceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

    @Configuration
    public class WebConfig implements WebMvcConfigurer {
        @Autowired
        private Interceptor interceptor;
        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(interceptor).addPathPatterns("/**").excludePathPatterns(
                   "/diary/login",//登录组件
                   "/swagger-ui.html"
           );
        }

}
