package com.pkrmarthala.productservicecapstone.controllers;

import com.pkrmarthala.productservicecapstone.dtos.CreateProductRequestDto;
import com.pkrmarthala.productservicecapstone.dtos.ProductResponseDto;
import com.pkrmarthala.productservicecapstone.exceptions.ProductNotFoundException;
import com.pkrmarthala.productservicecapstone.models.Product;
import com.pkrmarthala.productservicecapstone.services.ProductService;
import helpers.ProductHelper;
import helpers.CreateProductRequestDtoHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProductControllerTest {

    @MockitoBean
    @Qualifier("productDBService")
    public ProductService productService;

    @Autowired
    public ProductController productController;

    @Test
    public void testGetProductByIdReturnsProductResponseDto() throws ProductNotFoundException {

        Product dummyProduct = ProductHelper.createDummyProductForTest(1L);

        when(productService.getProductById(1L)).thenReturn(dummyProduct);

        ProductResponseDto productResponseDto = productController.getProductById(1L).getBody();

        assertAll(
                () -> assertEquals(1L, productResponseDto.getId()),
                () -> assertEquals("TestName_1", productResponseDto.getName()),
                () -> assertEquals("TestDescription_1", productResponseDto.getDescription()),
                () -> assertEquals(10.0, productResponseDto.getPrice()),
                () -> assertEquals(4, productResponseDto.getQuantity()),
                () -> assertEquals("TestImage_1.url", productResponseDto.getImageUrl()),
                () -> assertEquals("TestCategoryName_1", productResponseDto.getCategory())
        );

        verify(productService, times(1)).getProductById(1L);

    }


    @Test
    public void testGetProductByIdThrowsProductNotFoundException() throws ProductNotFoundException {

        when(productService.getProductById(1L)).thenThrow(ProductNotFoundException.class);

        // ProductResponseDto productResponseDto = productController.getProductById(1L);
        assertThrows(ProductNotFoundException.class, () -> productController.getProductById(1L));

        verify(productService, times(1)).getProductById(1L);
    }

    @Test
    public void testGetProductByIdHandlesNullGracefully() throws ProductNotFoundException {
        when(productService.getProductById(1L)).thenReturn(null) ;

        assertThrows(Exception.class, () -> productController.getProductById(1L));

        verify(productService, times(1)).getProductById(1L);
    }

    @Test
    public void testGetProductByIdWithInvalidId() throws ProductNotFoundException {
        assertThrows(IllegalArgumentException.class, () -> productController.getProductById(0L));
        verify(productService, never()).getProductById(anyLong());
    }

    @Test
    public void testGetAllProductsReturnsProductResponseDtoList() {

        List<Product> dummyProductList = ProductHelper.createDummyProductListForTest(-1);

        when(productService.getAllProducts()).thenReturn(dummyProductList);

        List<ProductResponseDto> expectedProductDtoList = dummyProductList.stream()
                .map(ProductResponseDto::fromProduct)
                .collect(Collectors.toList());

        List<ProductResponseDto> actualProductResponseDtoList = productController.getAllProducts();

        assertEquals(dummyProductList.size(), actualProductResponseDtoList.size());

        assertThat(actualProductResponseDtoList)
                .usingRecursiveComparison()
                .isEqualTo(expectedProductDtoList);
    }

    @Test
    public void testGetAllProductsReturnsEmptyList() throws ProductNotFoundException {

        when(productService.getAllProducts()).thenReturn(Collections.emptyList());

        assertTrue(productController.getAllProducts().isEmpty());

        verify(productService, times(1)).getAllProducts();

    }


    @Test
    public void testCreateProductReturnsProductResponseDto() throws ProductNotFoundException {
        // Arrange
        CreateProductRequestDto requestDto = CreateProductRequestDtoHelper.createProductRequestDto();

        Product dummyProduct = ProductHelper.createDummyProductForTest(1L);

        when(productService.createProduct(
                requestDto.getName(),
                requestDto.getDescription(),
                requestDto.getPrice(),
                requestDto.getQuantity(),
                requestDto.getCategory(),
                requestDto.getImageUrl()
        )).thenReturn(dummyProduct);

        // Act
        ProductResponseDto responseDto = productController.createProduct(requestDto);

        // Assert
        assertAll(
                () -> assertEquals(dummyProduct.getId(), responseDto.getId()),
                () -> assertEquals(dummyProduct.getName(), responseDto.getName()),
                () -> assertEquals(dummyProduct.getDescription(), responseDto.getDescription()),
                () -> assertEquals(dummyProduct.getPrice(), responseDto.getPrice()),
                () -> assertEquals(dummyProduct.getQuantity(), responseDto.getQuantity()),
                () -> assertEquals(dummyProduct.getImageUrl(), responseDto.getImageUrl()),
                () -> assertEquals(dummyProduct.getCategory().getName(), responseDto.getCategory())
        );

        // Verify
        verify(productService, times(1)).createProduct(
                requestDto.getName(),
                requestDto.getDescription(),
                requestDto.getPrice(),
                requestDto.getQuantity(),
                requestDto.getCategory(),
                requestDto.getImageUrl()
        );

    }


    @Test
    public void testCreateProductThrowsProductNotFoundException() throws ProductNotFoundException {
        CreateProductRequestDto requestDto = CreateProductRequestDtoHelper.createProductRequestDto();

        when(productService.createProduct(
                anyString(), anyString(), anyDouble(), anyLong(), anyString(), anyString()
        )).thenThrow(new ProductNotFoundException("Category not found"));

        assertThrows(ProductNotFoundException.class,
                () -> productController.createProduct(requestDto));

        verify(productService, times(1))
                .createProduct(anyString(), anyString(), anyDouble(), anyLong(), anyString(), anyString());
    }


    public void testCreateProduct() throws ProductNotFoundException {

    }


    public void testUpdateProductReturnsProductResponseDto() throws ProductNotFoundException {



    }



}