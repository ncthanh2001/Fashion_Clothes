package com.shop.Service;

import com.shop.Entity.Accounts;

import java.util.List;

public interface AccountService {

    List<Accounts> findAll() ;
    Accounts findByUsernameAndPassword(String username,String password);

    Accounts save(Accounts accounts);
}
