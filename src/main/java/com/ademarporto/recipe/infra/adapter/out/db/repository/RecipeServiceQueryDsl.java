package com.ademarporto.recipe.infra.adapter.out.db.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static com.ademarporto.recipe.infra.adapter.out.db.entity.QRecipeEntity.recipeEntity;
import static com.querydsl.core.types.dsl.Expressions.TRUE;

@Component
@RequiredArgsConstructor
public class RecipeServiceQueryDsl {

    public BooleanExpression ingredientEq(String ingredient) {
        if (StringUtils.isEmpty(ingredient)) {
            return TRUE.isTrue();
        }
        return recipeEntity.ingredientEntities.any().name.equalsIgnoreCase(ingredient);
    }

    public BooleanExpression ingredientNotEq(String ingredient) {
        if (StringUtils.isEmpty(ingredient)) {
            return TRUE.isTrue();
        }
        return recipeEntity.ingredientEntities.any().name.notEqualsIgnoreCase(ingredient);
    }

    public BooleanExpression servingsEq(Integer servings) {
        if (Objects.isNull(servings)) {
            return TRUE.isTrue();
        }
        return recipeEntity.servings.eq(servings);
    }

    public BooleanExpression vegetarianEq(Boolean vegetarian) {
        if (Objects.isNull(vegetarian)) {
            return TRUE.isTrue();
        }
        return recipeEntity.vegetarian.eq(vegetarian);
    }

    public BooleanExpression recipeInstructionLike(String searchBy) {
        if (StringUtils.isEmpty(searchBy)) {
            return TRUE.isTrue();
        }
        return recipeEntity.instructionEntities.any().description.likeIgnoreCase("%" + searchBy +"%");
    }

}
