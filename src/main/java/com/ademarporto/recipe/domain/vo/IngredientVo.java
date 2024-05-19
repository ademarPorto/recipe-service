package com.ademarporto.recipe.domain.vo;

import java.math.BigDecimal;

public record IngredientVo(String name,
                           BigDecimal quantity,
                           String unitOfMeasure) {
}
