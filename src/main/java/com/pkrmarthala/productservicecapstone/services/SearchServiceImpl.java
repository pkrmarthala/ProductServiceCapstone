package com.pkrmarthala.productservicecapstone.services;

import com.pkrmarthala.productservicecapstone.dtos.ProductResponseDto;
import com.pkrmarthala.productservicecapstone.models.Product;
import com.pkrmarthala.productservicecapstone.repositories.ProductRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchServiceImpl implements SearchService
{
    ProductRepository productRepository;

    public SearchServiceImpl(ProductRepository productRepository)
    {
        this.productRepository = productRepository;
    }

    @Override
    public Page<Product> Search(String query, int pageNumber, int pageSize, String sortBy)
    {
        Sort sort = Sort.by(sortBy);
        // Sort sort = Sort.by(Sort.Direction.DESC, sortBy);
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        return productRepository.findByNameContaining(query, pageable);

    }

}