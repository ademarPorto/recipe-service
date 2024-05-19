package com.ademarporto.recipe.model.usecases;

import com.ademarporto.recipe.application.port.out.RecipeOutputPort;
import com.ademarporto.recipe.domain.exception.RecipeNotFoundException;
import com.ademarporto.recipe.domain.usecases.RecipeUc;
import com.ademarporto.recipe.testutils.RecipeFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RecipeUcTest {

    @Mock
    private RecipeOutputPort outputPort;

    @InjectMocks
    private RecipeUc useCase;

    @Test
    void testCreateRecipe() {
        // Given
        var recipe = RecipeFactory.createRecipeModel();
        when(outputPort.persitRecipe(recipe)).thenReturn(recipe);

        // When
        var result = useCase.createRecipe(recipe);

        // Then
        assertThat(result, is(equalTo(recipe)));
    }

    @Test
    void testUpdateRecipe() {
        // Given
        var recipe = RecipeFactory.createRecipeModel();
        when(outputPort.findRecipeById(recipe.id())).thenReturn(Optional.of(recipe));
        when(outputPort.persitRecipe(recipe)).thenReturn(recipe);

        // When
        var result = useCase.updateRecipe(recipe);

        // Then
        assertThat(result, is(equalTo(recipe)));
    }

    @Test
    void testUpdateRecipeFail() {
        // Given
        var recipe = RecipeFactory.createRecipeModel();
        when(outputPort.findRecipeById(recipe.id())).thenReturn(Optional.empty());

        // When && Then
        assertThrows(RecipeNotFoundException.class, () -> useCase.updateRecipe(recipe));
    }

    @Test
    void testDeleteRecipe() {
        // Given
        var recipe = RecipeFactory.createRecipeModel();
        var recipeId = UUID.randomUUID();
        when(outputPort.findRecipeById(recipeId)).thenReturn(Optional.of(recipe));

        // When && Then
        useCase.deleteRecipe(recipeId.toString());
    }

    @Test
    void testDeleteRecipeFail() {
        // Given
        var recipeId = UUID.randomUUID();
        when(outputPort.findRecipeById(recipeId)).thenReturn(Optional.empty());

        // When && Then
        assertThrows(RecipeNotFoundException.class, () -> useCase.deleteRecipe(recipeId.toString()));
    }

    @Test
    void testFetchRecipe() {
        // Given
        var filters = RecipeFactory.createFilters();
        var recipe = RecipeFactory.createRecipeModel();
        when(outputPort.fetchRecipes(filters)).thenReturn(List.of(recipe));

        // When
        var result = useCase.fetchRecipes(filters);

        // Then
        assertFalse(result.isEmpty());
        assertThat(result.getFirst(), is(equalTo(recipe)));
    }
}
