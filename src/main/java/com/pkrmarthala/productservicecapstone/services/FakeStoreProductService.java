package com.pkrmarthala.productservicecapstone.services;

import com.pkrmarthala.productservicecapstone.dtos.FakeStoreProductRequestDto;
import com.pkrmarthala.productservicecapstone.dtos.FakeStoreProductResponseDto;
import com.pkrmarthala.productservicecapstone.exceptions.ProductNotFoundException;
import com.pkrmarthala.productservicecapstone.models.Product;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService {

    RestTemplate restTemplate;
    RedisTemplate<String, Object> redisTemplate;

    public FakeStoreProductService(@Qualifier("getRestTemplate") RestTemplate restTemplate,
                                   RedisTemplate<String, Object> redisTemplate) {
        this.restTemplate = restTemplate;
        this.redisTemplate = redisTemplate;
    }

    public Product getProductById(long id) throws ProductNotFoundException {

        Product productFromCache = (Product) redisTemplate.opsForValue().get("Product_"+ String.valueOf(id));
        if (productFromCache != null) {
            System.out.println("Product_"+ String.valueOf(id) +" fetched from the Cache");
            return productFromCache;
        }

        FakeStoreProductResponseDto fakeStoreProductResponseDto =
                restTemplate.getForObject(
                        "https://fakestoreapi.com/products/" + id,
                        FakeStoreProductResponseDto.class
                );

        if(fakeStoreProductResponseDto == null) {
            throw new ProductNotFoundException("The product for id: " + id + " does not exist!");
        }

        Product productFromFakeStore = fakeStoreProductResponseDto.toProduct();
        redisTemplate.opsForValue().set("Product_"+ String.valueOf(id), productFromFakeStore);
        System.out.println("Product_"+ String.valueOf(id) +" fetched from the DB");
        return productFromFakeStore;

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
                                                    long quantity,
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

    @Override
    public Product updateProduct(long id, String name, String description, double price, long quantity, String category, String imageUrl) throws ProductNotFoundException {
        return null;
    }

    @Override
    public Product deleteProductById(Long id) throws ProductNotFoundException {
        return null;
    }

}
