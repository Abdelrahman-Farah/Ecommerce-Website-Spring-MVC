package com.ecommerce.utils;

import com.ecommerce.entity.User;
import com.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


@Configuration
public class UserContextUtil {
    private static UserService userService;

    @Autowired
    public UserContextUtil(UserService userService) {
        this.userService = userService;
    }
    public static User getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userService.findUserByEmail(authentication.getName());
    }
}
