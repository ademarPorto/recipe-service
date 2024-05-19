package com.ademarporto.recipe.infra.adapter.out.db;

import com.ademarporto.recipe.application.port.out.RecipeOutputPort;
import com.ademarporto.recipe.domain.model.Recipe;
import com.ademarporto.recipe.infra.adapter.dto.RecipeQueryFilters;
import com.ademarporto.recipe.infra.adapter.mapper.RecipeMapper;
import com.ademarporto.recipe.infra.adapter.out.db.repository.RecipeRepository;
import com.ademarporto.recipe.infra.adapter.out.db.repository.RecipeServiceQueryDsl;
import com.querydsl.core.types.dsl.BooleanExpression;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.stream.Streams;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RecipeDbAdapter implements RecipeOutputPort {

    private final RecipeServiceQueryDsl recipeServiceQueryDsl;
    private final RecipeRepository recipeRepository;
    private final RecipeMapper recipeMapper;

    @Override
    public List<Recipe> fetchRecipes(RecipeQueryFilters filters) {
        var response = recipeRepository.findAll(getWhereCondition(filters));
        return Streams.of(response)
                .map(recipeMapper::toRecipeModel)
                .toList();
    }

    @Override
    @Transactional
    public Recipe persitRecipe(Recipe recipe) {
        var recipeEntity = recipeMapper.toRecipeEntity(recipe);
        var storedRecipe = recipeRepository.save(recipeEntity);
        return recipeMapper.toRecipeModel(storedRecipe);
    }

    @Override
    public Optional<Recipe> findRecipeById(UUID recipeId) {
        return recipeRepository.findById(recipeId)
                .map(recipeMapper::toRecipeModel);
    }

    @Override
    @Transactional
    public void deleteRecipe(UUID recipeId) {
        recipeRepository.deleteById(recipeId);
    }

    private BooleanExpression getWhereCondition(RecipeQueryFilters filters) {
        return recipeServiceQueryDsl.recipeInstructionLike(filters.searchBy())
                .and(recipeServiceQueryDsl.servingsEq(filters.servings()))
                .and(recipeServiceQueryDsl.vegetarianEq(filters.isVegetarian()))
                .and(Boolean.TRUE.equals(filters.isIngredientIncluded()) ?
                                recipeServiceQueryDsl.ingredientEq(filters.ingredient()) :
                                recipeServiceQueryDsl.ingredientNotEq(filters.ingredient())
                        );
    }
}
