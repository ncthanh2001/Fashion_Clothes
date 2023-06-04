package com.shop.Repository;

import com.shop.Entity.Accounts;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts, String>, JpaSpecificationExecutor<Accounts> {

    List<Accounts> findAll();
    Accounts findByUsernameAndPassword(String username,String password);

//    Accounts save(Accounts accounts);

}