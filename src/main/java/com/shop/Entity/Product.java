package com.shop.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Products")
public class Product  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @Nationalized
    @Column(name = "Name", nullable = false, length = 50)
    private String name;

    @Nationalized
    @Lob
    @Column(name = "Image")
    private String image;

    @Column(name = "Price", nullable = false, precision = 18)
    private BigDecimal price;

    @Column(name = "create_date", nullable = false)
    private LocalDate createDate;

    @Column(name = "Quantity", nullable = false)
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY,  cascade = CascadeType.MERGE )
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private Category category;

    @Column(name = "is_delete", nullable = false)
    private Boolean isDelete = false;


//    @OneToMany(mappedBy = "product")
//    private Set<DiscountedProduct> discountedProducts = new LinkedHashSet<>();

//    @OneToMany(mappedBy = "product")
//    private Set<OrderDetail> orderDetails = new LinkedHashSet<>();

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                ", createDate=" + createDate +
                ", quantity=" + quantity +
                ", category=" + category +
                ", isDelete=" + isDelete +
                '}';
    }
}