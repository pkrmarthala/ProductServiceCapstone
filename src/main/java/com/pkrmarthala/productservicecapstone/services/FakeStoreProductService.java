package com.pkrmarthala.productservicecapstone.services;

import com.pkrmarthala.productservicecapstone.dtos.FakeStoreProductRequestDto;
import com.pkrmarthala.productservicecapstone.dtos.FakeStoreProductResponseDto;
import com.pkrmarthala.productservicecapstone.exceptions.ProductNotFoundException;
import com.pkrmarthala.productservicecapstone.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService {

    RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Product getProductById(long id) throws ProductNotFoundException {
        FakeStoreProductResponseDto fakeStoreProductResponseDto =
                restTemplate.getForObject(
                        "https://fakestoreapi.com/products/" + id,
                        FakeStoreProductResponseDto.class
                );

        if(fakeStoreProductResponseDto == null) {
            throw new ProductNotFoundException("The product for id: " + id + " does not exist!");
        }
        return fakeStoreProductResponseDto.toProduct();

    }

    @Override
    public List<Product> getAllProducts() {

        FakeStoreProductResponseDto[] fakeStoreProductResponseDtos =
                restTemplate.getForObject(
                        "https://fakestoreapi.com/products/",
                        FakeStoreProductResponseDto[].class
                );

        List<Product> products = new ArrayList<>();

        for (FakeStoreProductResponseDto fakeStoreProductResponseDto : fakeStoreProductResponseDtos) {
            products.add(fakeStoreProductResponseDto.toProduct());
        }

        return products;
    }

    @Override
    public Product createProduct(String name,
                                                     String description,
                                                     double price,
                                                     String category,
                                                     String imageUrl)
    {
        FakeStoreProductRequestDto fakestoreProductRequestDto =
                new FakeStoreProductRequestDto();

        fakestoreProductRequestDto.setTitle(name);
        fakestoreProductRequestDto.setPrice(price);
        fakestoreProductRequestDto.setDescription(description);
        fakestoreProductRequestDto.setCategory(category);
        fakestoreProductRequestDto.setImage(imageUrl);

        // url, requestDto, responseDto
        FakeStoreProductResponseDto fakeStoreProductResponseDto =
                restTemplate.postForObject(
                        "https://fakestoreapi.com/products",
                        fakestoreProductRequestDto,
                        FakeStoreProductResponseDto.class);

        return fakeStoreProductResponseDto.toProduct();
    }

}
