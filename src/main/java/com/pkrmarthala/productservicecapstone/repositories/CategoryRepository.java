package com.pkrmarthala.productservicecapstone.repositories;

import com.pkrmarthala.productservicecapstone.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long>
{
    Optional<Category> findByName(String CategoryName);

    Category save(Category category);

}
