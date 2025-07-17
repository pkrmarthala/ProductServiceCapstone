package com.pkrmarthala.productservicecapstone.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product extends BaseModel {

    private String imageUrl;
    private Category category;
    private double price;
    private int quantity;

}
