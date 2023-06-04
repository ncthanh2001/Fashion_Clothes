package com.shop.Service.Impl;


import com.shop.Entity.Account;
import com.shop.Repository.AccountsRepository;
import com.shop.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class AccoutServiceImpl implements AccountService  {
    @Autowired
    AccountsRepository accountsRepository;

    @Override
    public List<Account> findAll() {
        return accountsRepository.findAll();
    }

    @Override
    public Account findByUsernameAndPassword(String username, String password) {
        return accountsRepository.findByUsernameAndPassword(username,password);
    }

    @Override
    public Account findByUsername(String username) {
        return accountsRepository.findByUsername(username);
    }

    @Override
    public Account save(Account accounts) {
        return accountsRepository.save(accounts);
    }


}
