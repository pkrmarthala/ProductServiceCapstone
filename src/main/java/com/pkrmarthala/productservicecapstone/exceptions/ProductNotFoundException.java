package com.pkrmarthala.productservicecapstone.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message)
    {
        super(message);
    }
}
