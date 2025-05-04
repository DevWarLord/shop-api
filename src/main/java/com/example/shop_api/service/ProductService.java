package com.example.shop_api.service;

import com.example.shop_api.model.Product;
import com.example.shop_api.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product updatedProduct) {
        updatedProduct.setId(id);
        return productRepository.save(updatedProduct);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> filterProducts(String name, Long categoryId, Double minPrice, Double maxPrice) {
        List<Product> filtered = productRepository.findAll();

        if (name != null && !name.isEmpty()) {
            filtered.retainAll(productRepository.findByNameContainingIgnoreCase(name));
        }

        if (categoryId != null) {
            filtered.retainAll(productRepository.findByCategoryId(categoryId));
        }

        if (minPrice != null && maxPrice != null) {
            filtered.retainAll(productRepository.findByPriceBetween(minPrice, maxPrice));
        }

        return filtered;
    }

}
