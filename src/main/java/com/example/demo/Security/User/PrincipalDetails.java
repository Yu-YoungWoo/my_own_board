package com.example.demo.Security.User;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.Mybatis.DAO.user;

public class PrincipalDetails implements UserDetails {

    private user user;

    public PrincipalDetails(user user) {
        this.user = user;
    }

    // 권한 관련 작업을 하기 위한 role return
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(() -> {
            return user.getRole().name();
        });
        
        return collection;
    }

    @Override
    public String getUsername() {
        return user.getId();
    }

    @Override
    public String getPassword() {
        return user.getPw();
    }

    // 계정이 만료 되었는지 (true: 만료X)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정이 잠겼는지 (true: 잠기지 않음)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 비밀번호가 만료되었는지 (true: 만료X)
    @Override
    public boolean isCredentialsNonExpired() {
        // 비밀번호 만료 여부를 여기에서 확인하고 반환합니다.
        // 예를 들어, 비밀번호 만료 기간을 체크하여 만료되었을 경우 false를 반환하고,
        // 만료되지 않았을 경우 true를 반환하도록 구현할 수 있습니다.
        // 실제 비밀번호 만료 체크 로직을 구현해야 합니다.
        // 만료되지 않았다면 true를 반환하도록 수정하세요.
        return true;
    }

    // 계정이 활성화(사용가능)인지 (true: 활성화)
    @Override
    public boolean isEnabled() {
        return true;
    }

    
}
