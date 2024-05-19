package com.ademarporto.recipe.testutils;

import com.ademarporto.recipe.domain.vo.InstructionVo;
import com.ademarporto.recipe.infra.adapter.out.db.entity.InstructionEntity;
import com.ademarporto.recipe.rest.spec.spec.Instruction;

import static com.ademarporto.recipe.testutils.RecipeFactory.STEP;
import static com.ademarporto.recipe.testutils.RecipeFactory.STEP_DESCRIPTION;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class InstructionFactory {

    private InstructionFactory() {
    }


    public static void assertInstruction(InstructionEntity instructionEntity, InstructionVo instructionVo) {
        assertThat(instructionEntity, is(notNullValue()));
        assertThat(instructionEntity.getStep(), is(equalTo(instructionVo.step())));
        assertThat(instructionEntity.getDescription(), is(equalTo(instructionVo.description())));
    }

    public static void assertInstructionVo(InstructionVo instructionVo, InstructionEntity instructionEntity) {
        assertThat(instructionVo, is(notNullValue()));
        assertThat(instructionVo.step(), is(equalTo(instructionEntity.getStep())));
        assertThat(instructionVo.description(), is(equalTo(instructionEntity.getDescription())));
    }

    public static InstructionVo createInstructionVo() {
        return new InstructionVo(STEP, STEP_DESCRIPTION);
    }


    public static Instruction createInstructionSpec() {
        var instruction = new Instruction();
        instruction.setStep(STEP);
        instruction.setDescription(STEP_DESCRIPTION);
        return instruction;
    }
}
