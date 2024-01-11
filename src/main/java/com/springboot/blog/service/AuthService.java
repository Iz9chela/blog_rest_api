package com.springboot.blog.service;

import com.springboot.blog.payload.RegisterDto;
import com.springboot.blog.payload.LoginDto;

public interface AuthService {

    String login(LoginDto loginDto);

    String register(RegisterDto registerDto);

}
