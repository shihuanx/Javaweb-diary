package com.shihuanx.diary.service;

import com.shihuanx.diary.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {
    User login(User user);
}
