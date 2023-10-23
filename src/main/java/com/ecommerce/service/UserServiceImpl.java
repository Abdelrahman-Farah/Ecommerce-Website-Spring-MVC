package com.ecommerce.service;

import com.ecommerce.dao.UserRepository;
import com.ecommerce.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public User findUserByEmail(String email){
        return userRepository.findUserByEmail(email);
    }

    @Override
    public void save(User user) {
        String plain = user.getPassword();
        user.setPassword(passwordEncoder.encode(plain));
        userRepository.save(user);
    }
}
