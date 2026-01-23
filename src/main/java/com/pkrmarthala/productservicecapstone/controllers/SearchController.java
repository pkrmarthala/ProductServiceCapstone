package com.pkrmarthala.productservicecapstone.controllers;

import com.pkrmarthala.productservicecapstone.dtos.ProductResponseDto;
import com.pkrmarthala.productservicecapstone.dtos.SearchRequestDto;
import com.pkrmarthala.productservicecapstone.models.Product;
import com.pkrmarthala.productservicecapstone.services.SearchService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SearchController
{
    SearchService searchService;

    public SearchController(SearchService searchService)
    {
        this.searchService = searchService;
    }


    @PostMapping("/search")
    public Page<ProductResponseDto> search(@RequestBody SearchRequestDto searchRequestDto)
    {
        Page<Product> productPage = searchService.Search(searchRequestDto.getQuery(),
                searchRequestDto.getPageNumber(),
                searchRequestDto.getPageSize(),
                searchRequestDto.getSortBy()
        );

        return getProductResponseDtoListFromProductPage(productPage);
    }

    @GetMapping("/search")
    public Page<ProductResponseDto> search(
            @RequestParam String query,
            @RequestParam int pageNumber,
            @RequestParam int pageSize,
            @RequestParam String sortBy
    )
    {
        Page<Product> productPage = searchService.Search(query, pageNumber, pageSize, sortBy);

        return getProductResponseDtoListFromProductPage(productPage);
    }


    public Page<ProductResponseDto> getProductResponseDtoListFromProductPage(Page<Product> productPage)
    {

        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();

        List<Product> products = productPage.getContent();

        for(Product product : products)
        {
            productResponseDtoList.add(
                    ProductResponseDto.fromProduct(product)
            );
        }

        return new PageImpl<>(productResponseDtoList, productPage.getPageable(), productPage.getTotalElements());
    }

}
