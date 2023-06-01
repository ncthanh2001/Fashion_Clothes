package com.shop.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Collection;

@Entity
@Table(name = "Accounts")
@Data
public class Accounts {

    @Id
    @Column(name = "Username", nullable = false, length = 50)
    private String username;

    @Column(name = "Password", nullable = false, length = 50)
    private String password;

    @Column(name = "Fullname", nullable = false, length = 100)
    private String fullname;

    @Column(name = "Email", nullable = false, length = 50)
    private String email;

    @Column(name = "Photo", nullable = true , length = 100)
    private String photo;

    @Column(name = "Activated", nullable = false)
    private boolean activated;

    @Column(name = "Admin", nullable = false)
    private boolean admin;

    @OneToMany(mappedBy = "accounts_or", fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()
    private Collection<Orders> orders;

  }
