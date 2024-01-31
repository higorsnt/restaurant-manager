package com.restaurant.manager.controller.product;

import com.restaurant.manager.business.product.ProductService;
import com.restaurant.manager.dto.product.CreateProductDto;
import com.restaurant.manager.dto.product.ProductDto;
import com.restaurant.manager.dto.product.UpdateProductDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @Operation(summary = "Save a product")
    @ApiResponse(
            responseCode = "201",
            description = "Saved product",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProductDto.class))}
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto create(CreateProductDto createProductDto) {
        return service.create(createProductDto);
    }

    @Operation(summary = "Update a product")
    @ApiResponse(
            responseCode = "200",
            description = "Updated product",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProductDto.class))}
    )
    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDto update(@PathVariable Long id, UpdateProductDto updateProductDto) {
        return service.update(id, updateProductDto);
    }

    @Operation(summary = "Delete a product")
    @ApiResponse(
            responseCode = "200",
            description = "The product was successfully deleted"
    )
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @Operation(summary = "List products")
    @ApiResponse(
            responseCode = "200",
            description = "List with all filtered products"
    )
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDto> list() {
        return service.list(null);
    }
}
