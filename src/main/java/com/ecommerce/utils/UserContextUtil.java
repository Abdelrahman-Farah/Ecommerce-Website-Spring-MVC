package com.ecommerce.utils;

import com.ecommerce.entity.User;
import com.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


@Configuration
public class UserContextUtil {
    //don't make the field as static as it should be manged by spring [abdelkarim]
    private static UserService userService;

    @Autowired
    //Static member userService accessed via instance reference [it's very critical here in your case]
    // static variable related to the class so any object can see it and the below line will update the
    // user service based on the entered user so may another user can use the value for another user [abdelkarim]

    public UserContextUtil(UserService userService) {
        this.userService = userService;
    }
    public static User getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userService.findUserByEmail(authentication.getName());
    }
}
