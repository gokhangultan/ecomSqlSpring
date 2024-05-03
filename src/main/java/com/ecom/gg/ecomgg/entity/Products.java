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
    private long id;
    private String description;
    private long category_id;
    private String images;
    //Best practice snake_case ile column vermeyi unutma
    @Column(name="name")
    private String name;
    private double price;
    private double rating;
    private Integer sell_count;
    private Integer stock;
    private Integer store_id;
    @Enumerated(EnumType.STRING)
    private Gender gender;
}
