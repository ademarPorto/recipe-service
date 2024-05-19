package com.ademarporto.recipe.infra.adapter.dto;

public record RecipeQueryFilters(String searchBy,
                                 Integer servings,
                                 String ingredient,
                                 Boolean isIngredientIncluded,
                                 Boolean isVegetarian) {
}
