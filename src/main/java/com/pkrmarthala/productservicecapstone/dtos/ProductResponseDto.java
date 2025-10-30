package com.pkrmarthala.productservicecapstone.dtos;

import com.pkrmarthala.productservicecapstone.models.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ProductResponseDto {

    private long id;
    private String name;
    private double price;
    private long  quantity;
    private String ImageUrl;
    private String description;
    private String category;
//
//    private Date createdAt;
//    private Date updatedAt;

    public static ProductResponseDto fromProduct(Product product) {

        if (product == null) { return null; }

        ProductResponseDto productResponseDto = new ProductResponseDto();

        productResponseDto.setId(product.getId());
        productResponseDto.setName(product.getName());
        productResponseDto.setDescription(product.getDescription());
        productResponseDto.setImageUrl(product.getImageUrl());
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setQuantity(product.getQuantity());
        productResponseDto.setCategory(product.getCategory().getName());

//        productResponseDto.setCreatedAt(product.getCreatedAt());
//        productResponseDto.setUpdatedAt(product.getUpdatedAt());

        return productResponseDto;
    }

}
