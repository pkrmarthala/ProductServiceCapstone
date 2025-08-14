package com.pkrmarthala.productservicecapstone.services;

import com.pkrmarthala.productservicecapstone.exceptions.ProductNotFoundException;
import com.pkrmarthala.productservicecapstone.models.Category;
import com.pkrmarthala.productservicecapstone.models.Product;
import com.pkrmarthala.productservicecapstone.repositories.CategoryRepository;
import com.pkrmarthala.productservicecapstone.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("productDBService")
// @Primary
public class ProductDBService implements ProductService
{

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductDBService(ProductRepository productRepository, CategoryRepository categoryRepository)
    {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getProductById(long id) throws ProductNotFoundException {

        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty())
            throw new ProductNotFoundException("Product with id: " + id + " does not exist in the database.");
        return optionalProduct.get();
    }

    @Override
    public List<Product> getAllProducts()
    {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(long id,
                                 String name,
                                 String description,
                                 double price,
                                 String category,
                                 String imageUrl)
    {

        Product product = new Product();

        product.setId(id);
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setImageUrl(imageUrl);

        Category categoryObj = getCategoryFromDB(category);
        categoryObj.setName(category);

        product.setCategory(categoryObj);

        return productRepository.save(product);
    }

    private Category getCategoryFromDB(String categoryName)
    {

        Optional<Category> optionalCategory = categoryRepository.findByName(categoryName);

        if(optionalCategory.isPresent()) {
            return optionalCategory.get();
        } else {
            Category category = new Category();
            category.setName(categoryName);
            return categoryRepository.save(category);
        }

    }

}
