package com.ecom.gg.ecomgg.entity;

//  POJO setter getters tablolar olusturmak Products bir pojo oldu
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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
    @NotNull
    @NotBlank
    private double price;


    @Column(name="description")
    @NotNull
    @NotBlank
    @Size(min = 3, max = 200)
    private String description;

    @Column(name="images")
    @Size(min = 3, max = 100)
    private String images;

    @Column(name="category")
    @NotNull
    @NotBlank
    @Size(min = 3, max = 45)
    private String category;


    @Column(name="name")
    @NotNull
    @NotBlank
    @Size(min = 3, max = 50)
    private String name;

    @Column(name="rating")
    @NotNull
    @NotBlank
    @Min(0)
    @Max(5)
    private double rating;

    @Column(name="sell_count")
    @NotNull
    @NotBlank
    @Min(0)
    private long sellCount;

    @Column(name="stock")
    @NotNull
    @NotBlank
    @Min(0)
    private long stock;

    @Column(name="store_id")
    @NotNull
    @NotBlank
    @Min(0)
    @Max(3)
    private long storeId;

    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(name="products_categories",schema="ecomgg",
            joinColumns = @JoinColumn(name="product_id"),
            inverseJoinColumns = @JoinColumn(name="category_id")
    )
    private List<Categories> categoriesList;

    public void addCategories(Categories categories){
        if(categoriesList == null){
            categoriesList = new ArrayList<>();
        }
        categoriesList.add(categories);
    }
}
