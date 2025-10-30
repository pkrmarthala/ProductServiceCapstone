package com.pkrmarthala.productservicecapstone.services;

import com.pkrmarthala.productservicecapstone.exceptions.ProductNotFoundException;
import com.pkrmarthala.productservicecapstone.models.Product;

import java.util.List;

public interface ProductService {

    public Product getProductById(long id) throws ProductNotFoundException;

    public List<Product> getAllProducts();

    public Product createProduct(String name,
                                    String description,
                                    double price,
                                    long quantity,
                                    String category,
                                    String imageUrl
    );

    public Product updateProduct(
            long id,
            String name,
            String description,
            double price,
            long quantity,
            String category,
            String imageUrl
    ) throws ProductNotFoundException;

    public Product deleteProductById(Long id) throws ProductNotFoundException;
}
