package com.pkrmarthala.productservicecapstone.services;

import com.pkrmarthala.productservicecapstone.dtos.ProductResponseDto;
import com.pkrmarthala.productservicecapstone.models.Product;
import org.springframework.data.domain.Page;

public interface SearchService
{
    Page<Product> Search(String query, int pageNumber, int pageSize, String sortBy);
}
