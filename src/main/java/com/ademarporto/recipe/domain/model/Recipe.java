package com.ademarporto.recipe.domain.model;

import com.ademarporto.recipe.domain.vo.IngredientVo;
import com.ademarporto.recipe.domain.vo.InstructionVo;

import java.util.List;
import java.util.UUID;

public record Recipe(UUID id,
                     String name,
                     String description,
                     Integer preparationTime ,
                     Integer cookTime,
                     Integer servings,
                     boolean vegetarian,
                     Category category,
                     List<IngredientVo> ingredients,
                     List<InstructionVo> instructions) {
}
