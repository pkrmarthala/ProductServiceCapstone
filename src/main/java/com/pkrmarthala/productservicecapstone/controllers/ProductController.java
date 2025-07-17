package com.pkrmarthala.productservicecapstone.controllers;

import com.pkrmarthala.productservicecapstone.dtos.ProductResponseDto;
import com.pkrmarthala.productservicecapstone.models.Product;
import com.pkrmarthala.productservicecapstone.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable long id) {

        Product product = productService.getProductById(id);

        return new ResponseEntity<>(ProductResponseDto.fromProduct(product), HttpStatus.ACCEPTED);

    }

}