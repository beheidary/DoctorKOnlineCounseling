package com.doctork.doctorkonlinecounseling.database.mappers.doctor;

import com.doctork.doctorkonlinecounseling.database.entities.doctor.ExpertiseEntity;
import com.doctork.doctorkonlinecounseling.domain.doctor.Expertise;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface ExpertiseEntityMapper {

    ExpertiseEntity modelToEntity(Expertise expertise);

    @Named("entityToModelWithDoctor")
    Expertise entityToModelWithDoctor(ExpertiseEntity expertiseEntity);

    @Mappings({@Mapping(target = "doctors",expression = "java(null)")})
    Expertise entityToModel(ExpertiseEntity expertiseEntity);



}
