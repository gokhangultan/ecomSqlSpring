package com.ecom.gg.ecomgg.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="categories", schema = "ecomgg")
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name="id")
    private long id;

    @Column(name="code")
    @NotNull
    @NotBlank
    @Size(min = 3, max = 40)
    private String code;

    @Column(name="img")
    @Size(min = 3, max = 100)
    private String image;

    @Column(name="rating")
    @NotNull
    @Min(0)
    @Max(5)
    private double rating;

    @Column(name="title")
    @NotNull
    @NotBlank
    @Size(min = 3, max = 50)
    private String title;


    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(name="products_categories",schema="ecomgg",
    joinColumns = @JoinColumn(name="category_id"), inverseJoinColumns = @JoinColumn(name="product_id"))
    private List<Products> productsList;
}
