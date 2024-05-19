package com.ademarporto.recipe.infra.mapper;

import com.ademarporto.recipe.infra.adapter.mapper.InstructionMapper;
import com.ademarporto.recipe.infra.adapter.mapper.InstructionMapperImpl;
import com.ademarporto.recipe.testutils.InstructionFactory;
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
@SpringBootTest(classes = {InstructionMapperImpl.class})
public class InstructionMapperTest {

    @Autowired
    private InstructionMapper mapper;

    @Test
    void testInstructionVoToEntity() {
        // Given
        var instructionVo = InstructionFactory.createInstructionVo();

        // When
        var instructionEntity = mapper.toInstructionEntity(instructionVo);

        // Then
        assertThat(instructionEntity, is(notNullValue()));
        assertThat(instructionEntity.getStep(), is(equalTo(instructionVo.step())));
        assertThat(instructionEntity.getDescription(), is(equalTo(instructionVo.description())));
    }

    @Test
    void testInstructionEntityToVo() {
        // Given
        var instructionEntity = mapper.toInstructionEntity(InstructionFactory.createInstructionVo());

        // When
        var instructionVo = mapper.toInstructionVo(instructionEntity);

        // Then
        assertThat(instructionVo, is(notNullValue()));
        assertThat(instructionVo.step(), is(equalTo(instructionEntity.getStep())));
        assertThat(instructionVo.description(), is(equalTo(instructionEntity.getDescription())));
    }
}
