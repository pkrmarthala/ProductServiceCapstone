package com.pkrmarthala.productservicecapstone.services;

import com.pkrmarthala.productservicecapstone.exceptions.ProductNotFoundException;
import com.pkrmarthala.productservicecapstone.models.Category;
import com.pkrmarthala.productservicecapstone.models.Product;
import com.pkrmarthala.productservicecapstone.repositories.CategoryRepository;
import com.pkrmarthala.productservicecapstone.repositories.ProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

@Service("productDBService")
@Primary
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
        Product product = optionalProduct.get();
        if(product.isDeleted()) {
            throw new ProductNotFoundException("Product with id: " + id + " does not exist in the database.");
        }
        return product;
    }

    @PersistenceContext
    private EntityManager entityManager;

    public List<Product> getAllProducts() {
        Session session = entityManager.unwrap(Session.class);
        session.enableFilter("deletedProductFilter").setParameter("isDeleted", false);

        return session.createQuery("from Product", Product.class).getResultList();
    }

//
//    @Override
//    public List<Product> getAllProducts()
//    {
//        return productRepository.findAll();
//    }

    @Override
    public Product createProduct(String name,
                                 String description,
                                 double price,
                                 long quantity,
                                 String category,
                                 String imageUrl)
    {

        Product product = new Product();

        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setQuantity(quantity);
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

    @Override
    public Product updateProduct(long id,
                                 String name,
                                 String description,
                                 double price,
                                 long quantity,
                                 String category,
                                 String imageUrl) throws ProductNotFoundException
    {

        Product existingProduct =
                productRepository
                        .findById(id)
                        .orElseThrow(() -> new ProductNotFoundException(
                                "Product with id: " + id + " does not exist in the database.")
                        );


        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            throw new ProductNotFoundException("Product with id: " + id + " does not exist in the database.");
            // createProduct(name, description, price, category, imageUrl);
        }

        Product product = optionalProduct.get();
        if(name != null && !name.equals(product.getName())) {
            product.setName(name);
        }
        if(description != null && !description.equals(product.getDescription())) {
            product.setDescription(description);
        }
        if(price != 0.0 && price != product.getPrice()) {
            product.setPrice(price);
        }
        if(quantity != 0 && quantity != product.getQuantity()) {
            product.setQuantity(quantity);
        }

        if(category != null && !category.equals(product.getCategory().getName())) {
            Category categoryObj = getCategoryFromDB(category);

            categoryObj.setName(category);
            product.setCategory(categoryObj);

        }
        if(imageUrl != null && !imageUrl.equals(product.getImageUrl())) {
            product.setImageUrl(imageUrl);
        }

        return productRepository.save(product);
    }

    @Override
    public Product deleteProductById(Long id) throws ProductNotFoundException {

        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty())
            throw new ProductNotFoundException("Product with id: " + id + " does not exist in the database.");

        Product product = optionalProduct.get();

        if (product.isDeleted()) {
            throw new ProductNotFoundException("Product with id: " + id + " is already deleted and cannot be deleted.");
        }

        product.setDeleted(true);
        productRepository.save(product);

        return product;

    }

}
