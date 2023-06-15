package com.ivanfranchin.bookapi.service;

import com.ivanfranchin.bookapi.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProducts();

    List<Product> getProductsContainingText(String text);

    Product validateAndGetProduct(String id);

    Product saveProduct(Product product);

    void deleteProduct(Product product);
    
    Product updateProduct(Product product);

}
