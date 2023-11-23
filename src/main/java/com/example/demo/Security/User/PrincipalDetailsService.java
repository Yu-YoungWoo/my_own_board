package com.example.demo.Security.User;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.Mybatis.DAO.User;
import com.example.demo.Mybatis.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        
        User findUser = userMapper.findUserById(id);

        if(findUser == null) {
            throw new UsernameNotFoundException("해당 유저를 찾을 수 없습니다.");
        }

        return new PrincipalDetails(findUser);
    }
}
