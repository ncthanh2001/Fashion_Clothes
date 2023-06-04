package com.shop.Service.Impl;


import com.shop.Entity.Accounts;
import com.shop.Repository.AccountsRepository;
import com.shop.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class AccoutServiceImpl implements AccountService  {
    @Autowired
    AccountsRepository accountsRepository;

    @Override
    public List<Accounts> findAll() {
        return accountsRepository.findAll();
    }

    @Override
    public Accounts findByUsernameAndPassword(String username, String password) {
        return accountsRepository.findByUsernameAndPassword(username,password);
    }

    @Override
    public Accounts save(Accounts accounts) {
        return accountsRepository.save(accounts);
    }


}
