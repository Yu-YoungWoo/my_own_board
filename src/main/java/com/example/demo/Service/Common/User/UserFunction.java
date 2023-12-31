package com.example.demo.Service.Common.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.example.demo.Mybatis.DAO.User;
import com.example.demo.Mybatis.mapper.UserMapper;

@Component
public class UserFunction {

    @Autowired
    private UserMapper userMapper;

    public User returnUserByAuthentication() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if(auth != null && auth.getName() != null && auth.isAuthenticated()) {
            String id = auth.getName();
            User findUser = userMapper.findUserById(id);

            return findUser;
        }
        return null;
    } 
    
}
