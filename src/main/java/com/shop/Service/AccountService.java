package com.shop.Service;

import com.shop.Entity.Accounts;

import java.util.List;

public interface AccountService {

    List<Accounts> findAll() ;
    Accounts findByUsername(String username);
}
