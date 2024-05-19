package com.ademarporto.recipe.out.db.repository;

import com.ademarporto.recipe.infra.adapter.mapper.RecipeMapperImpl;
import com.ademarporto.recipe.infra.adapter.out.db.RecipeDbAdapter;
import com.ademarporto.recipe.infra.adapter.out.db.repository.RecipeRepository;
import com.ademarporto.recipe.testutils.RecipeFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static com.ademarporto.recipe.testutils.RecipeFactory.createFilters;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("integration")
public class RecipeDbAdapterTest {

    @Autowired
    RecipeDbAdapter adapter;
    @Autowired
    private RecipeRepository repository;
    @Autowired
    private RecipeMapperImpl mapper;

    @BeforeEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void testPersistRecipe() {
        // Given
        var model = RecipeFactory.createRecipeModel();
        var response = adapter.persitRecipe(model);

        // When
        var storedRecipe = adapter.findRecipeById(response.id());

        // Then
        assertThat(storedRecipe, is(notNullValue()));
    }

    @Test
    void testDeleteRecipe() {
        // Given
        var model = RecipeFactory.createRecipeModel();
        var response = adapter.persitRecipe(model);
        var storedRecipe = adapter.findRecipeById(response.id());
        assertThat(storedRecipe, is(notNullValue()));

        // When
        adapter.deleteRecipe(response.id());
        storedRecipe = adapter.findRecipeById(response.id());

        // Then
       assertTrue(storedRecipe.isEmpty());
    }

    @Test
    void testFindRecipeById() {
        // Given
        var model = RecipeFactory.createRecipeModel();
        var response = adapter.persitRecipe(model);

        // When
        var storedRecipe = adapter.findRecipeById(response.id());

        // Then
        assertThat(storedRecipe, is(notNullValue()));
    }

    @Test
    void testFetchRecipes() {
        // Given
        var model = RecipeFactory.createRecipeModel();
        var response = adapter.persitRecipe(model);

        // When
        var storedRecipe = adapter.fetchRecipes(createFilters());

        // Then
        assertThat(storedRecipe, is(notNullValue()));
    }
}
