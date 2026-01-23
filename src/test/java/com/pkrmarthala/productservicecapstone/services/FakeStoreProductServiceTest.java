package com.pkrmarthala.productservicecapstone.services;

import com.pkrmarthala.productservicecapstone.dtos.FakeStoreProductResponseDto;
import com.pkrmarthala.productservicecapstone.exceptions.ProductNotFoundException;
import com.pkrmarthala.productservicecapstone.models.Product;
import helpers.FakeStoreProductDtoHelper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class FakeStoreProductServiceTest {

    RestTemplate restTemplate = Mockito.mock(RestTemplate.class);

    FakeStoreProductService fakeStoreProductService = new FakeStoreProductService(restTemplate);

    @Test
    public void testGetProductByIdReturnsProduct() throws ProductNotFoundException {
        // Arrange
        FakeStoreProductResponseDto dummyResponseDto =
                FakeStoreProductDtoHelper.createDummyFakeStoreProductResponseDto();

        when(restTemplate.getForObject("https://fakestoreapi.com/products/1",
                FakeStoreProductResponseDto.class
        )).thenReturn(dummyResponseDto);


        //Act
        Product product = fakeStoreProductService.getProductById(1L);


        // Assert
        assertAll(
                () -> assertEquals(1L, product.getId()),
                () -> assertEquals("Title", product.getName()),
                () -> assertEquals("Description", product.getDescription()),
                () -> assertEquals(10.0, product.getPrice()),
                () -> assertEquals("image.url", product.getImageUrl()),
                () -> assertEquals("Category", product.getCategory().getName())
        );
    }


    @Test
    public void testGetProductByIdWhenPassedNull() throws ProductNotFoundException {

        when(restTemplate.getForObject("https://fakestoreapi.com/products/1",
                FakeStoreProductResponseDto.class
        )).thenReturn(null);

        assertThrows(ProductNotFoundException.class,
                () -> fakeStoreProductService.getProductById(1L));

    }


    @Test
    public void testCreateProductReturnsProductWithId() {

        FakeStoreProductResponseDto dummyResponseDto =
            FakeStoreProductDtoHelper.createDummyFakeStoreProductResponseDto();

        when(restTemplate.postForObject(
                        eq("https://fakestoreapi.com/products"),
                        any(),
                        eq(FakeStoreProductResponseDto.class)))
                .thenReturn(dummyResponseDto);

        Product product = fakeStoreProductService.createProduct(
                "ProductName",
                "ProductDescription",
                10.0,
                30,
                "CategoryName",
                "image.url"
        );

        assertAll(
                () -> assertEquals(1L, product.getId()),
                () -> assertEquals("Title", product.getName()),
                () -> assertEquals("Description", product.getDescription()),
                () -> assertEquals(10.0, product.getPrice()),
                () -> assertEquals("image.url", product.getImageUrl()),
                () -> assertEquals("Category", product.getCategory().getName())
        );
    }

    @Test
    public void testCreateProductVerifyAPICalls() {
        FakeStoreProductResponseDto dummyResponseDto =
                FakeStoreProductDtoHelper.createDummyFakeStoreProductResponseDto();

        when(restTemplate.postForObject(
                eq("https://fakestoreapi.com/products"),
                any(),
                eq(FakeStoreProductResponseDto.class)))
                .thenReturn(dummyResponseDto);

        Product product = fakeStoreProductService.createProduct(
                "ProductName",
                "ProductDescription",
                10.0,
                30,
                "CategoryName",
                "image.url"
        );

verify(restTemplate, times(1))
        .postForObject(eq("https://fakestoreapi.com/products"),
                any(),
                eq(FakeStoreProductResponseDto.class));
    }

}