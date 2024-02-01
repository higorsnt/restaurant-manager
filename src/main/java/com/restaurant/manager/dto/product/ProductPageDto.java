package com.restaurant.manager.dto.product;

import com.restaurant.manager.entity.product.Product;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.domain.Page;

import java.util.List;

public record ProductPageDto(

        @ArraySchema(schema = @Schema(description = "Elements returned by the search", implementation = ProductDto.class))
        List<ProductDto> products,

        @Schema(description = "Current page with returned values", example = "3")
        Integer currentPage,

        @Schema(description = "Total number of elements returned by the search", example = "100")
        Long totalElements,

        @Schema(description = "Total number of pages containing the elements returned by the search", example = "5")
        Integer totalPages
) {
    public ProductPageDto(Page<Product> page) {
        this(
                page.stream().map(ProductDto::new).toList(),
                page.getNumber(),
                page.getTotalElements(),
                page.getTotalPages()
        );
    }
}
