package com.ademarporto.recipe.infra.adapter.mapper;

import com.ademarporto.recipe.domain.vo.InstructionVo;
import com.ademarporto.recipe.infra.adapter.out.db.entity.InstructionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface InstructionMapper {

    InstructionVo toInstructionVo(InstructionEntity instructionEntity);

    List<InstructionVo> toInstructionVo(List<InstructionEntity> instructionEntities);

    InstructionEntity toInstructionEntity(InstructionVo instructionVo);
}
