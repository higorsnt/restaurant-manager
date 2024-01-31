package com.restaurant.manager.entity.category;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(enumAsRef = true)
public enum Category {
    DRINK,
    APPETIZER,
    MAIN_COURSE,
    DESSERT,
}
