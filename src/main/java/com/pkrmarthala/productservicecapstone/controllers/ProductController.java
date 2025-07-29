package com.pkrmarthala.productservicecapstone.controllers;

import com.pkrmarthala.productservicecapstone.dtos.CreateProductRequestDto;
import com.pkrmarthala.productservicecapstone.dtos.ErrorDto;
import com.pkrmarthala.productservicecapstone.dtos.FakeStoreProductRequestDto;
import com.pkrmarthala.productservicecapstone.dtos.ProductResponseDto;
import com.pkrmarthala.productservicecapstone.exceptions.ProductNotFoundException;
import com.pkrmarthala.productservicecapstone.models.Product;
import com.pkrmarthala.productservicecapstone.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductResponseDto>
            getProductById(@PathVariable("id") long id)
            throws ProductNotFoundException
    {

        Product product = productService.getProductById(id);
        ProductResponseDto productResponseDto = ProductResponseDto.fromProduct(product);

        ResponseEntity<ProductResponseDto> responseEntity =
                new ResponseEntity<>(productResponseDto, HttpStatus.OK);

        return responseEntity;

        // return new ResponseEntity<>(ProductResponseDto.fromProduct(product), HttpStatus.ACCEPTED);

    }


    @GetMapping("/products")
    public List<ProductResponseDto> getAllProducts() {

        List<Product> products = productService.getAllProducts();

        List<ProductResponseDto> productResponseDtos = new ArrayList<>();

        productResponseDtos = products.stream().map(ProductResponseDto::fromProduct).toList();
//
//        for (Product product : products) {
//            ProductResponseDto productResponseDto = ProductResponseDto.fromProduct(product);
//            productResponseDtos.add(productResponseDto);
//        }

        return productResponseDtos;
    }

    @PostMapping("/products")
    public ProductResponseDto createProduct(@RequestBody
                            CreateProductRequestDto createProductRequestDto)
    {
        Product product = productService.createProduct(
                createProductRequestDto.getName(),
                createProductRequestDto.getDescription(),
                createProductRequestDto.getPrice(),
                createProductRequestDto.getCategory(),
                createProductRequestDto.getImageUrl()
        );

        ProductResponseDto productResponseDto = ProductResponseDto.fromProduct(product);

        return productResponseDto;

    }
}