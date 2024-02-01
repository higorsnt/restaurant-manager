package com.restaurant.manager.controller.product;

import com.restaurant.manager.business.product.ProductService;
import com.restaurant.manager.dto.exception.ExceptionMessage;
import com.restaurant.manager.dto.product.CreateProductDto;
import com.restaurant.manager.dto.product.ProductDto;
import com.restaurant.manager.dto.product.ProductPageDto;
import com.restaurant.manager.dto.product.UpdateProductDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ProductDto create(@Valid @RequestBody CreateProductDto createProductDto) {
        return service.create(createProductDto);
    }

    @Operation(summary = "Update a product")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Updated product",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductDto.class))}
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Product not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionMessage.class))}
            ),
    })
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProductDto> update(@PathVariable Long id, @Valid @RequestBody UpdateProductDto updateProductDto) {
        return ResponseEntity.ok(service.update(id, updateProductDto));
    }

    @Operation(summary = "Delete a product")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The product was successfully deleted"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Product not found",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionMessage.class))}
                    ),
            }
    )
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @Operation(summary = "List products")
    @ApiResponse(
            responseCode = "200",
            description = "List with all filtered products",
            content = {@Content(mediaType = "application/json",
                    schema =@Schema(implementation = ProductPageDto.class))}
    )
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ProductPageDto list(@RequestParam(required = false) String name,
                               @RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "10") int size) {
        PageRequest pageable = PageRequest.of(page, size);
        return service.list(name, pageable);
    }
}
