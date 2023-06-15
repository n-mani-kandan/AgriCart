package com.ivanfranchin.bookapi.mapper;

import com.ivanfranchin.bookapi.model.Product;
import com.ivanfranchin.bookapi.rest.dto.ProductDto;
import com.ivanfranchin.bookapi.rest.dto.CreateProductRequest;
import org.springframework.stereotype.Service;

@Service
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product toProduct(CreateProductRequest createProductRequest) {
        if (createProductRequest == null) {
            return null;
        }
        return new Product(createProductRequest.getId(), createProductRequest.getName(),createProductRequest.getPrice(),createProductRequest.getCount(),createProductRequest.getDescription());
    }

    @Override
    public ProductDto toProductDto(Product book) {
        if (book == null) {
            return null;
        }
        return new ProductDto(book.getId(), book.getName(),book.getPrice(),book.getCount(),book.getDescription());
    }
}
