package com.ademarporto.recipe.infra.mapper;

import com.ademarporto.recipe.infra.adapter.mapper.IngredientMapperImpl;
import com.ademarporto.recipe.infra.adapter.mapper.InstructionMapperImpl;
import com.ademarporto.recipe.infra.adapter.mapper.RecipeMapper;
import com.ademarporto.recipe.infra.adapter.mapper.RecipeMapperImpl;
import com.ademarporto.recipe.testutils.RecipeFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.ademarporto.recipe.testutils.IngredientFactory.assertIngredient;
import static com.ademarporto.recipe.testutils.IngredientFactory.assertIngredientVo;
import static com.ademarporto.recipe.testutils.InstructionFactory.assertInstruction;
import static com.ademarporto.recipe.testutils.InstructionFactory.assertInstructionVo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {RecipeMapperImpl.class, IngredientMapperImpl.class, InstructionMapperImpl.class})
public class RecipeMapperTest {

    @Autowired
    private RecipeMapper mapper;

    @Test
    void testRecipeVoToEntity() {
        // Given
        var recipeModel = RecipeFactory.createRecipeModel();

        // When
        var recipeEntity = mapper.toRecipeEntity(recipeModel);

        // Then
        assertThat(recipeEntity, is(notNullValue()));
        assertThat(recipeEntity.getName(), is(equalTo(recipeModel.name())));
        assertThat(recipeEntity.getDescription(), is(equalTo(recipeModel.description())));
        assertThat(recipeEntity.getPreparationTime(), is(equalTo(recipeModel.preparationTime())));
        assertThat(recipeEntity.getCookTime(), is(equalTo(recipeModel.cookTime())));
        assertThat(recipeEntity.getCategory().name(), is(equalTo(recipeModel.category().name())));
        assertFalse(recipeEntity.getIngredientEntities().isEmpty());
        assertIngredient(recipeEntity.getIngredientEntities().stream().findFirst().get(),
                recipeModel.ingredients().getFirst());
        assertFalse(recipeEntity.getInstructionEntities().isEmpty());
        assertInstruction(recipeEntity.getInstructionEntities().stream().findFirst().get(),
                recipeModel.instructions().getFirst());
    }


    @Test
    void testRecipeEntityToVo() {
        // Given
        var recipeEntity = mapper.toRecipeEntity(RecipeFactory.createRecipeModel());

        // When
        var recipeModel = mapper.toRecipeModel(recipeEntity);

        // Then
        assertThat(recipeModel, is(notNullValue()));
        assertThat(recipeModel.name(), is(equalTo(recipeEntity.getName())));
        assertThat(recipeModel.description(), is(equalTo(recipeEntity.getDescription())));
        assertThat(recipeModel.preparationTime(), is(equalTo(recipeEntity.getPreparationTime())));
        assertThat(recipeModel.cookTime(), is(equalTo(recipeEntity.getCookTime())));
        assertThat(recipeModel.category().name(), is(equalTo(recipeEntity.getCategory().name())));
        assertFalse(recipeModel.ingredients().isEmpty());
        assertIngredientVo(recipeModel.ingredients().stream().findFirst().get(),
                recipeEntity.getIngredientEntities().stream().findFirst().get());
        assertFalse(recipeModel.instructions().isEmpty());
        assertInstructionVo(recipeModel.instructions().getFirst(),
                recipeEntity.getInstructionEntities().stream().findFirst().get());
    }
}
