package com.shop.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Orders")
@Data
public class Orders {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Id", nullable = false)
    private int id;
//    @Basic
//    @Column(name = "Username", nullable = false, length = 50,insertable=false, updatable=false)
//    private String username;
    @Basic
    @Column(name = "CreateDate", nullable = false)
    private Date createDate;
    @Basic
    @Column(name = "Address", nullable = false, length = 100)
    private String address;

    @ManyToOne
    @JoinColumn(name="Username" )
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    Accounts accounts_or;

    @OneToMany(mappedBy = "orders")
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()
    private Collection<OrderDetails> orders_detail;


  }
