package com.ademarporto.recipe.infra.adapter.in.api.controller;

import com.ademarporto.recipe.application.port.in.RecipeInputPort;
import com.ademarporto.recipe.infra.adapter.mapper.FindRequestMapper;
import com.ademarporto.recipe.infra.adapter.mapper.RecipeMapper;
import com.ademarporto.recipe.rest.spec.api.V1Api;
import com.ademarporto.recipe.rest.spec.spec.CreateRecipesResponse;
import com.ademarporto.recipe.rest.spec.spec.RecipesRequest;
import com.ademarporto.recipe.rest.spec.spec.RecipesResponse;
import com.ademarporto.recipe.rest.spec.spec.UpdateRecipesResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RecipeController implements V1Api {

    private final FindRequestMapper findRequestMapper;
    private final RecipeMapper recipeMapper;
    private final RecipeInputPort inputPort;

    @Override
    public ResponseEntity<RecipesResponse> getRecipes(
            @Size(min = 1, max = 50) @Valid String searchBy, @Valid Integer servings,
            @Size(min = 1, max = 100) @Valid String ingredient, @Valid Boolean isIngredientIncluded,
            @Valid   Boolean isVegetarian) {
        log.info("Hit [getRecipes] endpoint.");

        var recipeQueryFilters = findRequestMapper.toRecipeQueryFilters(searchBy, servings, ingredient,
                isIngredientIncluded, isVegetarian);

        var recipes = inputPort.fetchRecipes(recipeQueryFilters);
        var response = new RecipesResponse();
        response.setRecipes(recipeMapper.toRecipesResponse(recipes));
        response.setTotalOfRecords(recipes.size());

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<CreateRecipesResponse> createRecipes(@Valid @RequestBody RecipesRequest recipesRequest) {
        log.info("Hit [createRecipes] endpoint.");

        var request = recipeMapper.toRecipeModel(recipesRequest.getRecipe());
        var recipe = inputPort.createRecipe(request);
        var response = new CreateRecipesResponse();
        response.setRecipe(recipeMapper.toRecipesResponse(recipe));

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    public ResponseEntity<UpdateRecipesResponse> updateRecipes(@PathVariable("recipe-id") String recipeId,
                                                               @Valid @RequestBody RecipesRequest recipesRequest) {
        log.info("Hit [updateRecipes] endpoint.");

        var recipe = recipeMapper.toRecipeModel(recipeId, recipesRequest.getRecipe());
        var updatedRecipe = inputPort.updateRecipe(recipe);

        var response = new UpdateRecipesResponse();
        response.setRecipe(recipeMapper.toRecipesResponse(updatedRecipe));

        return ResponseEntity.ok(response);
    }

    public ResponseEntity<Void> deleteRecipes(@PathVariable("recipe-id") String recipeId) {
        log.info("Hit [deleteRecipe] endpoint.");
        inputPort.deleteRecipe(recipeId);
        return ResponseEntity.noContent().build();
    }
}
