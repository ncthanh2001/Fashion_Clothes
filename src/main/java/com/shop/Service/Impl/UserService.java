//package com.shop.Service.Impl;
//
//import com.shop.Entity.Accounts;
//import com.shop.Entity.CustomUserDetails;
//import com.shop.Service.AccountService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserService implements UserDetailsService {
//    @Autowired
//    private AccountService accountService;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        // Kiểm tra xem user có tồn tại trong database không?
//        Accounts user = accountService.findByUsername(username);
//        if (user == null) {
//            throw new UsernameNotFoundException(username);
//        }
//        return new CustomUserDetails(user);
//    }
//}
