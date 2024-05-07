package com.doctork.doctorkonlinecounseling.database.mappers;

import com.doctork.doctorkonlinecounseling.database.entities.doctor.ExpertiseEntity;
import com.doctork.doctorkonlinecounseling.domain.SpecificModels.TopExpertises;
import com.doctork.doctorkonlinecounseling.domain.doctor.Expertise;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface ExpertiseEntityMapper {
    @Mappings({@Mapping(target = "doctors",expression = "java(null)")})

    ExpertiseEntity modelToEntity(Expertise expertise);

    @Named("entityToModelWithDoctor")
    @Mappings({@Mapping(target = "doctors",expression = "java(null)")})
    Expertise entityToModelWithDoctor(ExpertiseEntity expertiseEntity);

    @Mappings({@Mapping(target = "doctors",expression = "java(null)")})
    Expertise entityToModel(ExpertiseEntity expertiseEntity);

    @Mappings({@Mapping(target = "doctors",expression = "java(null)")})
    TopExpertises topEntityToModel(ExpertiseEntity expertiseEntity);



}
