package com.ademarporto.recipe.application.port.in;

import com.ademarporto.recipe.domain.model.Recipe;
import com.ademarporto.recipe.infra.adapter.dto.RecipeQueryFilters;

import java.util.List;

public interface RecipeInputPort {

    List<Recipe> fetchRecipes(RecipeQueryFilters filters);

    Recipe createRecipe(Recipe recipe);

    Recipe updateRecipe(Recipe recipe);

    void deleteRecipe(String recipeId);
}
