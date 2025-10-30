package com.pkrmarthala.productservicecapstone.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductRequestDto {
    private long id;
    private String name;
    private String description;
    private double price;
    private long quantity;
    private String imageUrl;
    private String category;

}
