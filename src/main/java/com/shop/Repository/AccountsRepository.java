package com.shop.Repository;

import com.shop.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountsRepository extends JpaRepository<Account, String>, JpaSpecificationExecutor<Account> {

    List<Account> findAll();
    Account findByUsernameAndPassword(String username,String password);

    Account  findByUsername(String username);

//    Accounts save(Accounts accounts);

}