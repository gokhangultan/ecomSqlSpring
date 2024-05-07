package com.ecom.gg.ecomgg.entity;

//  POJO setter getters tablolar olusturmak Products bir pojo oldu
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="products",schema="ecomgg")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //Best practice snake_case ile column vermeyi unutma
    @Column(name="id")
    private long id;

    @Column(name="price")
    private double price;

    @Column(name="description")
    private String description;

    @Column(name="images")
    private String images;

    @Column(name="category")
    private String category;

    @Column(name="name")
    private String name;

    @Column(name="rating")
    private double rating;

    @Column(name="sell_count")
    private long sellCount;

    @Column(name="stock")
    private long stock;

    @Column(name="store_id")
    private long storeId;
}
