package com.doctork.doctorkonlinecounseling.api.mappers;

import com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.Physician.ExpertiseInputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.SpecificResultDtos.TopExpertisesDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.physician.ExpertiseOutputDto;
import com.doctork.doctorkonlinecounseling.domain.Expertise.TopExpertises;
import com.doctork.doctorkonlinecounseling.domain.Expertise.Expertise;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ExpertiseMapper {

    Expertise inputToModel(ExpertiseInputDto expertiseInputDTO);

    @Mappings({@Mapping(target = "physicians",expression = "java(null)")})
    Expertise inputToModelWithoutDoctor(ExpertiseInputDto expertiseInputDTO);

    ExpertiseOutputDto modelToOutput(Expertise expertise);

    @Mappings({@Mapping(target = "physicians",expression = "java(null)")})
    TopExpertisesDto topModelToOutput(TopExpertises expertise);



}
