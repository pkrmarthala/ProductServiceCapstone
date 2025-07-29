package com.pkrmarthala.productservicecapstone.dtos;

import lombok.Getter;
import lombok.Setter;

// The DTO that the client sends to create a product (adding new product to the FakeStore Products List).

@Getter
@Setter
public class FakeStoreProductRequestDto {

    // private long id;
    private String title;
    private double price;
    private String description;
    private String category;
    private String image;

}

