package com.pkrmarthala.productservicecapstone.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel {

    private String imageUrl;
    private double price;
    private int quantity;

    @ManyToOne
    private Category category;
}
