package com.ademarporto.recipe.infra.in.api.controller;

import com.ademarporto.recipe.application.port.in.RecipeInputPort;
import com.ademarporto.recipe.application.port.out.RecipeOutputPort;
import com.ademarporto.recipe.domain.model.Recipe;
import com.ademarporto.recipe.infra.adapter.mapper.RecipeMapperImpl;
import com.ademarporto.recipe.infra.adapter.out.db.RecipeDbAdapter;
import com.ademarporto.recipe.infra.adapter.out.db.repository.RecipeRepository;
import com.ademarporto.recipe.rest.spec.spec.CreateRecipesResponse;
import com.ademarporto.recipe.rest.spec.spec.Error;
import com.ademarporto.recipe.rest.spec.spec.RecipesResponse;
import com.ademarporto.recipe.testutils.RecipeFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static com.ademarporto.recipe.domain.exception.ErrorMessage.RECIPE_NOT_FOUND_CODE;
import static com.ademarporto.recipe.domain.exception.ErrorMessage.RECIPE_NOT_FOUND_MESSAGE;
import static com.ademarporto.recipe.testutils.RecipeFactory.INGREDIENT_NAME;
import static com.ademarporto.recipe.testutils.RecipeFactory.SERVINGS;
import static com.ademarporto.recipe.testutils.RecipeFactory.STEP_DESCRIPTION;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("integration")
@AutoConfigureMockMvc
public class RecipeControllerIT {

    private static final String RECIPE_ENDPOINT = "/v1/recipes";
    private static final String RECIPE_ID_PARAM_ENDPOINT = "/v1/recipes/";

    @Autowired
    private RecipeOutputPort outputPort;
    @Autowired
    private RecipeDbAdapter adapter;
    @Autowired
    private RecipeRepository repository;
    @Autowired
    private RecipeInputPort inputPort;
    @Autowired
    RecipeMapperImpl recipeMapper;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    private Recipe storedRecipe;

    @BeforeEach
    void setup() {
        repository.deleteAll();
        var model = RecipeFactory.createRecipeModel();
        storedRecipe = adapter.persitRecipe(model);
    }

    @Test
    void when_CreateRecipe_ThenReturn_Created() throws Exception {
        var request = RecipeFactory.createRecipeRequest();
        var mvcResult = mockMvc
                .perform(post(RECIPE_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        var response = objectMapper.readValue(mvcResult, CreateRecipesResponse.class);

        assertThat(response, is(notNullValue()));
        assertThat(response.getRecipe(), is(notNullValue()));
        assertThat(response.getRecipe().getId(), is(notNullValue()));
    }

    @Test
    void when_UpdateRecipe_ThenReturn_Ok() throws Exception {
        var request = RecipeFactory.createRecipeRequest();
        request.getRecipe().setVegetarian(Boolean.TRUE);
        request.getRecipe().setId(storedRecipe.id());

        var mvcResult = mockMvc
                .perform(put(RECIPE_ID_PARAM_ENDPOINT + storedRecipe.id())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        var response = objectMapper.readValue(mvcResult, CreateRecipesResponse.class);

        assertThat(response, is(notNullValue()));
        assertThat(response.getRecipe(), is(notNullValue()));
        assertThat(response.getRecipe().getId(), is(notNullValue()));
        assertThat(response.getRecipe().getId(), is(equalTo(storedRecipe.id())));
        assertThat(response.getRecipe().getVegetarian(), is(equalTo(Boolean.TRUE)));
    }

    @Test
    void when_UpdateRecipe_InvalidId_ThenReturn_NotFound() throws Exception {
        var request = RecipeFactory.createRecipeRequest();
        request.getRecipe().setVegetarian(Boolean.TRUE);
        request.getRecipe().setId(storedRecipe.id());
        var invalidId = UUID.randomUUID();

        var mvcResult = mockMvc
                .perform(put(RECIPE_ID_PARAM_ENDPOINT + invalidId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andReturn().getResponse().getContentAsString();

        var response = objectMapper.readValue(mvcResult, Error.class);

        assertThat(response, is(notNullValue()));
        assertThat(response.getCode(), is(equalTo(RECIPE_NOT_FOUND_CODE)));
        assertThat(response.getMessage(),
                is(equalTo(String.format(RECIPE_NOT_FOUND_MESSAGE, invalidId))));
    }

    @Test
    void when_DeleteRecipe_ThenReturn_NoContent() throws Exception {
        mockMvc
                .perform(delete(RECIPE_ID_PARAM_ENDPOINT + storedRecipe.id())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        var result = repository.findById(storedRecipe.id());

        assertTrue(result.isEmpty());
    }

    @Test
    void when_GetRecipes_ThenReturn_Recipes() throws Exception {
        var mvcResult = mockMvc
                .perform(get(RECIPE_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        var response = objectMapper.readValue(mvcResult, RecipesResponse.class);


        assertThat(response, is(notNullValue()));
        assertThat(response.getRecipes(), is(notNullValue()));
        assertThat(response.getRecipes().getFirst().getId(), is(notNullValue()));
    }

    @Test
    void when_GetRecipes_SearchBy_Filter_ThenReturn_Recipes() throws Exception {
        var mvcResult = mockMvc
                .perform(get(RECIPE_ENDPOINT)
                        .queryParam("searchBy", STEP_DESCRIPTION)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        var response = objectMapper.readValue(mvcResult, RecipesResponse.class);

        assertThat(response, is(notNullValue()));
        assertThat(response.getRecipes(), is(notNullValue()));
        assertThat(response.getRecipes().getFirst().getInstructions(), is(notNullValue()));
        assertThat(response.getRecipes().getFirst().getInstructions().getFirst().getDescription(),
                is(equalTo(STEP_DESCRIPTION)));

    }

    @Test
    void when_GetRecipes_Servings_Filter_ThenReturn_Recipes() throws Exception {
        var mvcResult = mockMvc
                .perform(get(RECIPE_ENDPOINT)
                        .queryParam("servings", String.valueOf(SERVINGS))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        var response = objectMapper.readValue(mvcResult, RecipesResponse.class);

        assertThat(response, is(notNullValue()));
        assertThat(response.getRecipes(), is(notNullValue()));
        assertThat(response.getRecipes().getFirst().getServings(), is(equalTo(SERVINGS)));
    }

    @Test
    void when_GetRecipes_Ingredient_Included_Filter_ThenReturn_Recipes() throws Exception {
        var mvcResult = mockMvc
                .perform(get(RECIPE_ENDPOINT)
                        .queryParam("ingredient", INGREDIENT_NAME)
                        .queryParam("isIngredientIncluded", String.valueOf(true))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        var response = objectMapper.readValue(mvcResult, RecipesResponse.class);

        assertThat(response, is(notNullValue()));
        assertThat(response.getRecipes(), is(notNullValue()));
        assertThat(response.getRecipes().getFirst().getIngredients(), is(notNullValue()));
        assertThat(response.getRecipes().getFirst().getIngredients().getFirst().getName(),
                is(equalTo(INGREDIENT_NAME)));
    }

    @Test
    void when_GetRecipes_Ingredient_Excluded_Filter_ThenReturn_EmptyList() throws Exception {
        var mvcResult = mockMvc
                .perform(get(RECIPE_ENDPOINT)
                        .queryParam("ingredient", INGREDIENT_NAME)
                        .queryParam("isIngredientIncluded", String.valueOf(false))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        var response = objectMapper.readValue(mvcResult, RecipesResponse.class);

        assertThat(response, is(notNullValue()));
        assertTrue(response.getRecipes().isEmpty());
    }

    @Test
    void when_GetRecipes_IsVegetarian_Filter_ThenReturn_Recipes() throws Exception {
        var mvcResult = mockMvc
                .perform(get(RECIPE_ENDPOINT)
                        .queryParam("vegetarian", String.valueOf(false))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        var response = objectMapper.readValue(mvcResult, RecipesResponse.class);

        assertThat(response, is(notNullValue()));
        assertThat(response.getRecipes(), is(notNullValue()));
        assertFalse(response.getRecipes().getFirst().getVegetarian());
    }

    @Test
    void when_GetRecipes_All_Filters_ThenReturn_Recipes() throws Exception {
        var mvcResult = mockMvc
                .perform(get(RECIPE_ENDPOINT)
                        .queryParam("searchBy", STEP_DESCRIPTION)
                        .queryParam("servings", String.valueOf(SERVINGS))
                        .queryParam("ingredient", INGREDIENT_NAME)
                        .queryParam("isIngredientIncluded", String.valueOf(false))
                        .queryParam("vegetarian", String.valueOf(false))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        var response = objectMapper.readValue(mvcResult, RecipesResponse.class);

        assertThat(response, is(notNullValue()));
        assertThat(response.getRecipes(), is(notNullValue()));
    }

}
