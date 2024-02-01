package com.restaurant.manager.business.product;

import com.restaurant.manager.dto.product.CreateProductDto;
import com.restaurant.manager.dto.product.ProductDto;
import com.restaurant.manager.dto.product.ProductPageDto;
import com.restaurant.manager.dto.product.UpdateProductDto;
import com.restaurant.manager.entity.product.Product;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    @Autowired
    ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public ProductDto create(CreateProductDto createProductDto) {
        Product product = createProductDto.toEntity();
        product = repository.save(product);
        return new ProductDto(product);
    }

    @Override
    @Transactional
    public ProductDto update(Long productId, UpdateProductDto updateProductDto) {
        Product product = repository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Product with id %d not found.", productId)));

        if (!Objects.equals(updateProductDto.name(), product.getName())) {
            product.setName(updateProductDto.name());
        }

        if (!Objects.equals(updateProductDto.price(), product.getPrice())) {
            product.setPrice(updateProductDto.price());
        }

        if (!Objects.equals(updateProductDto.category(), product.getCategory())) {
            product.setCategory(updateProductDto.category());
        }

        return new ProductDto(product);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public ProductPageDto list(String name, Pageable pageable) {
        Page<Product> result;
        if (name != null) {
            result = repository.findByNameContaining(name, pageable);
        } else {
            result = repository.findAll(pageable);
        }

        return new ProductPageDto(result);
    }

    @Override
    public Product findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Product with id %d not found.", id)));
    }


}
