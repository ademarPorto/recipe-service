package com.ademarporto.recipe.out.db.repository;

import com.ademarporto.recipe.infra.adapter.out.db.repository.RecipeServiceQueryDsl;
import org.junit.jupiter.api.Test;

import static com.querydsl.core.types.dsl.Expressions.TRUE;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RecipeServiceQueryDslTest {

    @Test
    void given_IngredientEqNull_Expect_TrueIsTrue() {
        var recipeServiceQueryDsl = new RecipeServiceQueryDsl();
        var expression = recipeServiceQueryDsl.ingredientEq(null);
        assertEquals(TRUE.isTrue(), expression);
    }

    @Test
    void given_IngredientEq_Expect_Expression() {
        var recipeServiceQueryDsl = new RecipeServiceQueryDsl();
        var expression = recipeServiceQueryDsl.ingredientEq("Salmon");
        var condition = "eqIc(any(recipeEntity.ingredientEntities).name,Salmon)";
        assertEquals(condition, expression.toString());
    }

    @Test
    void given_IngredientNotEqNull_Expect_TrueIsTrue() {
        var recipeServiceQueryDsl = new RecipeServiceQueryDsl();
        var expression = recipeServiceQueryDsl.ingredientNotEq(null);
        assertEquals(TRUE.isTrue(), expression);
    }

    @Test
    void given_IngredientNotEq_Expect_Expression() {
        var recipeServiceQueryDsl = new RecipeServiceQueryDsl();
        var expression = recipeServiceQueryDsl.ingredientNotEq("Salmon");
        var condition = "!(eqIc(any(recipeEntity.ingredientEntities).name,Salmon))";
        assertEquals(condition, expression.toString());
    }

    @Test
    void given_ServingsEqNull_Expect_TrueIsTrue() {
        var recipeServiceQueryDsl = new RecipeServiceQueryDsl();
        var expression = recipeServiceQueryDsl.servingsEq(null);
        assertEquals(TRUE.isTrue(), expression);
    }

    @Test
    void given_ServingsEq_Expect_Expression() {
        var recipeServiceQueryDsl = new RecipeServiceQueryDsl();
        var expression = recipeServiceQueryDsl.servingsEq(1);
        var condition = "recipeEntity.servings = 1";
        assertEquals(condition, expression.toString());
    }

    @Test
    void given_VegetarianEqNull_Expect_TrueIsTrue() {
        var recipeServiceQueryDsl = new RecipeServiceQueryDsl();
        var expression = recipeServiceQueryDsl.vegetarianEq(null);
        assertEquals(TRUE.isTrue(), expression);
    }

    @Test
    void given_VegetarianEq_Expect_Expression() {
        var recipeServiceQueryDsl = new RecipeServiceQueryDsl();
        var expression = recipeServiceQueryDsl.vegetarianEq(Boolean.TRUE);
        var condition = "recipeEntity.vegetarian = true";
        assertEquals(condition, expression.toString());
    }

    @Test
    void given_RecipeInstructionLikeNull_Expect_TrueIsTrue() {
        var recipeServiceQueryDsl = new RecipeServiceQueryDsl();
        var expression = recipeServiceQueryDsl.recipeInstructionLike(null);
        assertEquals(TRUE.isTrue(), expression);
    }

    @Test
    void given_RecipeInstructionLike_Expect_Expression() {
        var recipeServiceQueryDsl = new RecipeServiceQueryDsl();
        var expression = recipeServiceQueryDsl.recipeInstructionLike("Oven");
        var condition = "lower(any(recipeEntity.instructionEntities).description) like %oven%";
        assertEquals(condition, expression.toString());
    }
}
