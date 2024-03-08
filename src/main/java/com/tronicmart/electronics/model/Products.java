package com.tronicmart.electronics.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Data
@ToString
@Table(name="products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;
    @Column
    private String imgurl;
    @Column
    private double price;
    @Column
    private String description;

    //many to one relationship with category
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "category_id")
    Category category;


}
