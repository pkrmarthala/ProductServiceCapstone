package com.pkrmarthala.productservicecapstone.dtos;

import com.pkrmarthala.productservicecapstone.models.Category;
import com.pkrmarthala.productservicecapstone.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductResponseDto {

    private long id;
    private String title;
    private String description;
    private double price;
    private long quantity;
    private String image;
    private String category;

    public Product toProduct() {

        Product product = new Product();
        product.setId(id);
        product.setName(title);
        product.setDescription(description);
        product.setPrice(price);
        product.setImageUrl(image);

        Category cat = new Category();
        cat.setName(category);
        product.setCategory(cat);

        return product;
    }

}
