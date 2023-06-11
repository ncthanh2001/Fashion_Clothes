package com.shop.Repository;

import com.shop.Entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountsRepository extends JpaRepository<Account, String>, JpaSpecificationExecutor<Account> {

    Account findByUsernameAndPassword(String username,String password);

    Account  findByUsername(String username);


    Page<Account> findAll(Specification<Account> spec, Pageable pageable);

    Page<Account> findByActivated(boolean active , Pageable pageable);
}