package com.shop.Service;

import com.shop.Entity.Account;
import com.shop.Entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AccountService {
List<Account> findAll();
    Page<Account> findAll(Pageable pageable);
    Account findByUsernameAndPassword(String username,String password);
    Account findByUsername(String username);
    Account save(Account accounts);
    List<Account> findByActivated(boolean active);

}
