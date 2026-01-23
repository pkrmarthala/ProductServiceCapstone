package com.pkrmarthala.productservicecapstone.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchRequestDto
{
    private String query;
    private int pageNumber;
    private int pageSize;
    private String sortBy;
}
