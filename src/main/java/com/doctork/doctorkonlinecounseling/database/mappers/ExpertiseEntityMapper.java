package com.doctork.doctorkonlinecounseling.database.mappers;

import com.doctork.doctorkonlinecounseling.database.entities.Expertise.ExpertiseEntity;
import com.doctork.doctorkonlinecounseling.domain.Expertise.TopExpertises;
import com.doctork.doctorkonlinecounseling.domain.Expertise.Expertise;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface ExpertiseEntityMapper {
    @Mappings({@Mapping(target = "physicians",expression = "java(null)")})

    ExpertiseEntity modelToEntity(Expertise expertise);

    @Named("entityToModelWithDoctor")
    @Mappings({@Mapping(target = "physicians",expression = "java(null)")})
    Expertise entityToModelWithDoctor(ExpertiseEntity expertiseEntity);

    @Mappings({@Mapping(target = "physicians",expression = "java(null)")})
    Expertise entityToModel(ExpertiseEntity expertiseEntity);

    @Mappings({@Mapping(target = "physicians",expression = "java(null)")})
    TopExpertises topEntityToModel(ExpertiseEntity expertiseEntity);



}
