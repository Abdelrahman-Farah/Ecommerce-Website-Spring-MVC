package com.ecommerce.service;

import com.ecommerce.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    User findUserByEmail(String email);

    void save(User user);
}
