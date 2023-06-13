package com.shop.Service.Impl;

import com.shop.Entity.Account;
import com.shop.Entity.CustomUserDetails;
import com.shop.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserDetailsService {
    @Autowired
    AccountService accountService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account  = accountService.findByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(account);
    }
}
