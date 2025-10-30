package com.pkrmarthala.productservicecapstone.repositories;

import com.pkrmarthala.productservicecapstone.models.Category;
import com.pkrmarthala.productservicecapstone.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long>
{
    Product save(Product product);

    List<Product> findAll();

    Optional<Product> findById(long id);

    Product deleteById(long id);

    // Query By Methods
    List<Product> findByCategory(Category category);

    // Declarative Query
    List<Product> findByCategory_Name(String categoryName);

    // Hibernate Query Language
    @Query("SELECT p FROM Product p where p.category.name = :categoryName")
    List<Product> getProductByCategoryName(@Param("categoryName") String categoryName);

    // Native Queries
    @Query(value = CustomQuery.GET_PRODUCT_BY_CATEGORY_NAME, nativeQuery = true)
    List<Product> getProductByCategoryNameNative(@Param("categoryName") String categoryName);

}
