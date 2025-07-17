package com.pkrmarthala.productservicecapstone.dtos;

import com.pkrmarthala.productservicecapstone.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDto {

    private long id;
    private String name;
    private double price;
    private String ImageUrl;
    private String description;
    private String category;

    public static ProductResponseDto fromProduct(Product product) {

        if (product == null) { return null; }

        ProductResponseDto productResponseDto = new ProductResponseDto();

        productResponseDto.setId(product.getId());
        productResponseDto.setName(product.getName());
        productResponseDto.setDescription(product.getDescription());
        productResponseDto.setImageUrl(product.getImageUrl());
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setCategory(product.getCategory().getName());

        return productResponseDto;
    }

}
