package com.pkrmarthala.productservicecapstone.services;

import com.pkrmarthala.productservicecapstone.exceptions.ProductNotFoundException;
import com.pkrmarthala.productservicecapstone.models.Product;

import java.util.List;

public interface ProductService {

    public Product getProductById(long id) throws ProductNotFoundException;

    public List<Product> getAllProducts();

    public Product createProduct(long id,
                                    String name,
                                    String description,
                                    double price,
                                    String category,
                                    String imageUrl
    );
}
