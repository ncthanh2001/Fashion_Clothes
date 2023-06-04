package com.shop.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Accounts")
public class Account {
    @Id
    @Nationalized
    @Column(name = "Username", nullable = false, length = 50)
    private String username;

    @Nationalized
    @Column(name = "Password", nullable = false, length = 50)
    private String password;

    @Nationalized
    @Column(name = "Fullname", nullable = false, length = 100)
    private String fullname;

    @Nationalized
    @Column(name = "Email", nullable = false, length = 50)
    private String email;

    @Nationalized
    @Column(name = "Photo", length = 100)
    private String photo;

    @Column(name = "Activated", nullable = false)
    private Boolean activated = false;

    @Column(name = "Admin", nullable = false)
    private Boolean admin = false;

    @OneToMany(mappedBy = "username")
    private Set<Order> orders = new LinkedHashSet<>();

}