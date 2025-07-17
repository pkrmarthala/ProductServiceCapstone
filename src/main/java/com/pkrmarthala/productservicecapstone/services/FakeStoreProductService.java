package com.pkrmarthala.productservicecapstone.services;

import com.pkrmarthala.productservicecapstone.dtos.FakeStoreProductDto;
import com.pkrmarthala.productservicecapstone.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FakeStoreProductService implements ProductService {

    RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Product getProductById(long id) {
        FakeStoreProductDto fakeStoreProductDto =
                restTemplate.getForObject(
                        "https://fakestoreapi.com/products/" + id,
                        FakeStoreProductDto.class
                );

        return fakeStoreProductDto.toProduct();

    }

}
