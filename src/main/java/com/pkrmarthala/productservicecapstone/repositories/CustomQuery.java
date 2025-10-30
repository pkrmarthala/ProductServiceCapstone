package com.pkrmarthala.productservicecapstone.repositories;

public class CustomQuery {
    public static final String GET_PRODUCT_BY_CATEGORY_NAME = "SELECT * FROM product where category_id IN" +
            "(SELECT category_id FROM category WHERE name = :categoryName)";
}
