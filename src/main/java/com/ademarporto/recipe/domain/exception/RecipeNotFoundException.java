package com.ademarporto.recipe.domain.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RecipeNotFoundException extends RuntimeException {
    private final String code;

    public RecipeNotFoundException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
