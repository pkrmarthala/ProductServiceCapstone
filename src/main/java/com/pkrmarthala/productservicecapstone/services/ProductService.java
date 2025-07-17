package com.pkrmarthala.productservicecapstone.services;

import com.pkrmarthala.productservicecapstone.models.Product;
import org.springframework.stereotype.Service;

public interface ProductService {

    public Product getProductById(long id);


}
