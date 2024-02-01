package com.restaurant.manager.business.product;

import com.restaurant.manager.dto.product.CreateProductDto;
import com.restaurant.manager.dto.product.ProductDto;
import com.restaurant.manager.dto.product.ProductPageDto;
import com.restaurant.manager.dto.product.UpdateProductDto;
import com.restaurant.manager.entity.product.Product;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    ProductDto create(CreateProductDto createProductDto);

    ProductDto update(Long id, UpdateProductDto updateProductDto);

    void delete(Long id);

    ProductPageDto list(String name, Pageable pageable);

    Product findById(Long id);

}
