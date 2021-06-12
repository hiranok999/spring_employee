package com.example.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import com.example.employee.mapper.UserMapper;
import com.example.employee.model.UserModel;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StringUtils.isEmpty(username)) {
        	throw new UsernameNotFoundException("");
        }

        UserModel user = userMapper.selectUserByName(username);

        // ユーザが存在しない場合
        if (user == null) {
        	throw new UsernameNotFoundException("");
        }
        // アカウントの有効期限切れ、アカウントのロック、パスワードの有効期限切れ、ユーザの無効を判定
        if (!user.isAccountNonExpired() || !user.isAccountNonLocked() || !user.isCredentialsNonExpired() || !user.isEnabled()) {
            throw new UsernameNotFoundException("");
        }

        return user;
    }

}