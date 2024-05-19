package com.ademarporto.recipe.domain.usecases;

import com.ademarporto.recipe.application.port.in.RecipeInputPort;
import com.ademarporto.recipe.application.port.out.RecipeOutputPort;
import com.ademarporto.recipe.domain.exception.RecipeNotFoundException;
import com.ademarporto.recipe.domain.model.Recipe;
import com.ademarporto.recipe.infra.adapter.dto.RecipeQueryFilters;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

import static com.ademarporto.recipe.domain.exception.ErrorMessage.RECIPE_NOT_FOUND_CODE;
import static com.ademarporto.recipe.domain.exception.ErrorMessage.RECIPE_NOT_FOUND_MESSAGE;

@Component
@RequiredArgsConstructor
public class RecipeUc implements RecipeInputPort {

    private final RecipeOutputPort outputPort;

    @Override
    public List<Recipe> fetchRecipes(RecipeQueryFilters filters) {
        return outputPort.fetchRecipes(filters);
    }

    @Override
    public Recipe createRecipe(Recipe recipe) {
        return outputPort.persitRecipe(recipe);
    }

    @Override
    public Recipe updateRecipe(Recipe recipe) {
        failIfRecipeNotPresent(recipe.id());
        return outputPort.persitRecipe(recipe);
    }

    @Override
    public void deleteRecipe(String recipeId) {
        var uuidRecipe = UUID.fromString(recipeId);
        failIfRecipeNotPresent(uuidRecipe);
        outputPort.deleteRecipe(uuidRecipe);
    }

    private void failIfRecipeNotPresent(UUID recipeId) {
        outputPort.findRecipeById(recipeId)
                .orElseThrow(() -> new RecipeNotFoundException(RECIPE_NOT_FOUND_CODE,
                        String.format(RECIPE_NOT_FOUND_MESSAGE, recipeId)));
    }
}
