package com.restaurant.manager.dto.exception;

import io.swagger.v3.oas.annotations.media.Schema;

public record ExceptionMessage(
        @Schema(description = "Error message generated during the request")
        String message
) {
}
