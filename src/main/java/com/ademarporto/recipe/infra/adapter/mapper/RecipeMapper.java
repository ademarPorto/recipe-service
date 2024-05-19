package com.ademarporto.recipe.infra.adapter.mapper;

import com.ademarporto.recipe.domain.model.Recipe;
import com.ademarporto.recipe.infra.adapter.out.db.entity.RecipeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN,
uses = {IngredientMapper.class, InstructionMapper.class})
public interface RecipeMapper {

    com.ademarporto.recipe.rest.spec.spec.Recipe toRecipesResponse(Recipe recipe);

    List<com.ademarporto.recipe.rest.spec.spec.Recipe> toRecipesResponse(List<Recipe> recipes);

    @Mapping(target = "ingredients", source = "ingredientEntities")
    @Mapping(target = "instructions", source = "instructionEntities")
    Recipe toRecipeModel(RecipeEntity recipeEntity);

    Recipe toRecipeModel(com.ademarporto.recipe.rest.spec.spec.Recipe recipe);

    @Mapping(target = "id", source = "recipeId")
    Recipe toRecipeModel(String recipeId, com.ademarporto.recipe.rest.spec.spec.Recipe recipe);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "ingredientEntities", source = "ingredients")
    @Mapping(target = "instructionEntities", source = "instructions")
    RecipeEntity toRecipeEntity(Recipe recipe);


    @Mapping(target = "id", source = "id")
    @Mapping(target = "ingredientEntities", source = "ingredients")
    @Mapping(target = "instructionEntities", source = "instructions")
    RecipeEntity toUpdateRecipeEntity(Recipe recipe);

}
