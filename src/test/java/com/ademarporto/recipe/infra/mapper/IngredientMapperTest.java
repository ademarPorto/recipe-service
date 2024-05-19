package com.ademarporto.recipe.infra.mapper;

import com.ademarporto.recipe.infra.adapter.mapper.IngredientMapper;
import com.ademarporto.recipe.infra.adapter.mapper.IngredientMapperImpl;
import com.ademarporto.recipe.testutils.IngredientFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {IngredientMapperImpl.class})
public class IngredientMapperTest {

    @Autowired
    private IngredientMapper mapper;

    @Test
    void testIngredientVoToEntity() {
        // Given
        var ingredientVo = IngredientFactory.createIngredientVo();

        // When
        var ingredientEntity = mapper.toIngredientEntity(ingredientVo);

        // Then
        assertThat(ingredientEntity, is(notNullValue()));
        assertThat(ingredientEntity.getName(), is(equalTo(ingredientVo.name())));
        assertThat(ingredientEntity.getQuantity(), is(equalTo(ingredientVo.quantity())));
        assertThat(ingredientEntity.getUnitOfMeasure(), is(equalTo(ingredientVo.unitOfMeasure())));
    }

    @Test
    void testIngredientEntityToVo() {
        // Given
        var ingredientEntity = mapper.toIngredientEntity(IngredientFactory.createIngredientVo());

        // When
        var ingredientVo = mapper.toIngredientVo(ingredientEntity);

        // Then
        assertThat(ingredientVo, is(notNullValue()));
        assertThat(ingredientVo.name(), is(equalTo(ingredientEntity.getName())));
        assertThat(ingredientVo.quantity(), is(equalTo(ingredientEntity.getQuantity())));
        assertThat(ingredientVo.unitOfMeasure(), is(equalTo(ingredientEntity.getUnitOfMeasure())));
    }
}
