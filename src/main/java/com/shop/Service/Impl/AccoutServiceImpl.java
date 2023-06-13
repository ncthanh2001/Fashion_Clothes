package com.shop.Service.Impl;


import com.shop.Entity.Account;
import com.shop.Repository.AccountsRepository;
import com.shop.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Page<Account> findAll(Pageable pageable) {
        return accountsRepository.findAll(pageable);
    }

    @Override
    public Account findByUsername(String username) {
        return accountsRepository.findByUsername(username);
    }

    @Override
    public Account save(Account accounts) {
        return accountsRepository.save(accounts);
    }

    @Override
    public Page<Account> findByActivated(boolean active,Pageable pageable) {
        return accountsRepository.findByActivated(active,pageable);
    }

    @Override
    public Account delete(Account account,boolean active) {
            account.setActivated( active);
        return accountsRepository.save(account);
    }

    @Override
    public Optional<Account> findByUsernameAndEmail(String username, String email) {
        return accountsRepository.findByUsernameAndEmail(username,email);
    }


}
