package com.ademarporto.recipe.testutils;

import com.ademarporto.recipe.domain.vo.IngredientVo;
import com.ademarporto.recipe.infra.adapter.out.db.entity.IngredientEntity;
import com.ademarporto.recipe.rest.spec.spec.Ingredient;

import static com.ademarporto.recipe.testutils.RecipeFactory.INGREDIENT_NAME;
import static com.ademarporto.recipe.testutils.RecipeFactory.QUANTITY;
import static com.ademarporto.recipe.testutils.RecipeFactory.UNIT_OF_MEASURE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class IngredientFactory {

    private IngredientFactory() {
    }

    public static void assertIngredient(IngredientEntity ingredientEntity, IngredientVo ingredientVo) {
        assertThat(ingredientEntity, is(notNullValue()));
        assertThat(ingredientEntity.getName(), is(equalTo(ingredientVo.name())));
        assertThat(ingredientEntity.getQuantity(), is(equalTo(ingredientVo.quantity())));
        assertThat(ingredientEntity.getUnitOfMeasure(), is(equalTo(ingredientVo.unitOfMeasure())));
    }

    public static void assertIngredientVo(IngredientVo ingredientVo, IngredientEntity ingredientEntity) {
        assertThat(ingredientVo, is(notNullValue()));
        assertThat(ingredientVo.name(), is(equalTo(ingredientEntity.getName())));
        assertThat(ingredientVo.quantity(), is(equalTo(ingredientEntity.getQuantity())));
        assertThat(ingredientVo.unitOfMeasure(), is(equalTo(ingredientEntity.getUnitOfMeasure())));
    }


    public static IngredientVo createIngredientVo() {
        return new IngredientVo(INGREDIENT_NAME,
                QUANTITY,
                UNIT_OF_MEASURE);
    }

    public static Ingredient createIngredientSpec() {
        var ingredient = new Ingredient();
        ingredient.setName(INGREDIENT_NAME);
        ingredient.setQuantity(QUANTITY.doubleValue());
        ingredient.setUnitOfMeasure(UNIT_OF_MEASURE);
        return ingredient;
    }
}
