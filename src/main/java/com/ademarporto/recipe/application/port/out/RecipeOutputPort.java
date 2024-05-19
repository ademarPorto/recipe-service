package com.ademarporto.recipe.application.port.out;

import com.ademarporto.recipe.domain.model.Recipe;
import com.ademarporto.recipe.infra.adapter.dto.RecipeQueryFilters;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RecipeOutputPort {

    List<Recipe> fetchRecipes(RecipeQueryFilters filters);

    Recipe persitRecipe(Recipe recipe);

    Optional<Recipe> findRecipeById(UUID recipeId);

    void deleteRecipe(UUID recipeId);

}
