package com.shop.Service;

import com.shop.Entity.Account;
import com.shop.Entity.Account;

import java.util.List;

public interface AccountService {

    List<Account> findAll() ;
    Account findByUsernameAndPassword(String username,String password);
    Account findByUsername(String username);
    Account save(Account accounts);
}
