package com.ivanfranchin.bookapi.mapper;

import com.ivanfranchin.bookapi.model.Product;

import com.ivanfranchin.bookapi.rest.dto.ProductDto;
import com.ivanfranchin.bookapi.rest.dto.CreateProductRequest;

public interface ProductMapper {

	Product toProduct(CreateProductRequest createproductRequest);

	ProductDto toProductDto(Product product);
}