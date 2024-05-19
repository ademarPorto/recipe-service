package com.ademarporto.recipe.testutils;


import com.ademarporto.recipe.domain.model.Category;
import com.ademarporto.recipe.domain.model.Recipe;
import com.ademarporto.recipe.infra.adapter.dto.RecipeQueryFilters;
import com.ademarporto.recipe.rest.spec.spec.RecipeCategory;
import com.ademarporto.recipe.rest.spec.spec.RecipesRequest;

import java.math.BigDecimal;
import java.util.List;

import static com.ademarporto.recipe.testutils.IngredientFactory.createIngredientSpec;
import static com.ademarporto.recipe.testutils.IngredientFactory.createIngredientVo;
import static com.ademarporto.recipe.testutils.InstructionFactory.createInstructionSpec;
import static com.ademarporto.recipe.testutils.InstructionFactory.createInstructionVo;

public class RecipeFactory {

    public static final String RECIPE_NAME = "Test Recipe";
    public static final String RECIPE_DESCRIPTION = "Test Description";
    public static final int PREPARATION_TIME = 30;
    public static final int COOK_TIME = 15;
    public static final int SERVINGS = 4;
    public static final boolean IS_VEGETARIAN = false;
    public static final String INGREDIENT_NAME = "Test Ingredient";
    public static final BigDecimal QUANTITY = new BigDecimal("2.0");
    public static final String UNIT_OF_MEASURE = "KG";
    public static final String STEP_DESCRIPTION = "Test Step";
    public static final int STEP = 1;

    private RecipeFactory() {
    }

    public static Recipe createRecipeModel() {
        return new Recipe(null,
                RECIPE_NAME,
                RECIPE_DESCRIPTION,
                PREPARATION_TIME,
                COOK_TIME,
                SERVINGS,
                IS_VEGETARIAN,
                Category.APPETIZER,
                List.of(createIngredientVo()),
                List.of(createInstructionVo()));
    }

    public static com.ademarporto.recipe.rest.spec.spec.Recipe createRecipeSpec() {
        var recipe =  new com.ademarporto.recipe.rest.spec.spec.Recipe();
        recipe.setName(RECIPE_NAME);
        recipe.setDescription(RECIPE_DESCRIPTION);
        recipe.setPreparationTime(PREPARATION_TIME);
        recipe.setCookTime(COOK_TIME);
        recipe.setServings(SERVINGS);
        recipe.setVegetarian(IS_VEGETARIAN);
        recipe.setCategory(RecipeCategory.APPETIZER);
        recipe.addIngredientsItem(createIngredientSpec());
        recipe.addInstructionsItem(createInstructionSpec());
        return recipe;
    }

    public static RecipesRequest createRecipeRequest() {
        var request = new RecipesRequest();
        request.setRecipe(createRecipeSpec());
        return request;
    }

    public static RecipeQueryFilters createFilters() {
        return new RecipeQueryFilters(STEP_DESCRIPTION,
                SERVINGS,
                INGREDIENT_NAME,
                true,
                false);
    }
}
