package com.example.javaspringblog.config;

import com.example.javaspringblog.dao.UserDAO;
import com.example.javaspringblog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityUserDetailsService implements UserDetailsService {

    private final UserDAO userDAO;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDAO
                .findUsersByUserName(username)
                .map(SecurityUser::new)
                .orElseThrow(()->new UsernameNotFoundException("No such user with name:" + username));

    }
}
