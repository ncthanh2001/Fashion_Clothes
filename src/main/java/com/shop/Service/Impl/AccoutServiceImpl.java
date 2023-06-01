package com.shop.Service.Impl;


import com.shop.Entity.Accounts;
import com.shop.Repository.AccountsRepository;
import com.shop.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class AccoutServiceImpl implements AccountService {
    @Autowired
    AccountsRepository accountsRepository;

    @Override
    public List<Accounts> findAll() {
        return accountsRepository.findAll();
    }

    @Override
    public Accounts findByUsername(String username) {
        return accountsRepository.findByUsername(username);
    }


}
