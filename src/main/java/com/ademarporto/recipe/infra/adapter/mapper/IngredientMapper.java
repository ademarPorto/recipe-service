package com.ademarporto.recipe.infra.adapter.mapper;

import com.ademarporto.recipe.domain.vo.IngredientVo;
import com.ademarporto.recipe.infra.adapter.out.db.entity.IngredientEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface IngredientMapper {

    IngredientVo toIngredientVo(IngredientEntity ingredientEntity);

    List<IngredientVo> toIngredientVo(List<IngredientEntity> ingredientEntities);

    IngredientEntity toIngredientEntity(IngredientVo ingredient);

}
