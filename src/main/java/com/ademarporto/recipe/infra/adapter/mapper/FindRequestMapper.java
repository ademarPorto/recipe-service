package com.ademarporto.recipe.infra.adapter.mapper;

import com.ademarporto.recipe.infra.adapter.dto.RecipeQueryFilters;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface FindRequestMapper {

    RecipeQueryFilters toRecipeQueryFilters(String searchBy, Integer servings, String ingredient,
                                            Boolean isIngredientIncluded, Boolean isVegetarian);
}
