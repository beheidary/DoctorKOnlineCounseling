package com.doctork.doctorkonlinecounseling.api.mappers;

import com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.PhysicianDetails.SicknessInputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.PhysicianDetails.SicknessOutputDto;
import com.doctork.doctorkonlinecounseling.domain.PhysicianDetails.Sickness;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PhysicianDetailsMapper {

    Set<SicknessOutputDto> sicknessModelToDto (Set<Sickness> sicknesses);

    Set<Sickness> sicknessDtoToModel (Set<SicknessInputDto> sicknessInputDtos);

    Sickness sicknessDtoToModel (SicknessInputDto sicknessInputDto);


}
