package com.doctork.doctorkonlinecounseling.api.mappers.Expertise;

import com.doctork.doctorkonlinecounseling.api.dtos.inputDTOs.doctor.ExpertiseInputDTO;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDTOs.SpecificResultDtos.TopExpertisesDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDTOs.doctor.ExpertiseOutputDTO;
import com.doctork.doctorkonlinecounseling.database.entities.doctor.ExpertiseEntity;
import com.doctork.doctorkonlinecounseling.domain.SpecificModels.TopExpertises;
import com.doctork.doctorkonlinecounseling.domain.doctor.Expertise;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ExpertiseMapper {

    Expertise inputToModel(ExpertiseInputDTO expertiseInputDTO);

    @Mappings({@Mapping(target = "doctors",expression = "java(null)")})
    Expertise inputToModelWithoutDoctor(ExpertiseInputDTO expertiseInputDTO);

    ExpertiseOutputDTO modelToOutput(Expertise expertise);

    @Mappings({@Mapping(target = "doctors",expression = "java(null)")})
    TopExpertisesDto topModelToOutput(TopExpertises expertise);



}
