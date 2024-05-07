package com.ecom.gg.ecomgg.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="categories", schema = "ecomgg")
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name="category_id")
    private long categoryId;

    @Column(name="code")
    private String code;

    @Column(name="img")
    private String image;

    @Column(name="rating")
    private double rating;

    @Column(name="title")
    private String title;
}
