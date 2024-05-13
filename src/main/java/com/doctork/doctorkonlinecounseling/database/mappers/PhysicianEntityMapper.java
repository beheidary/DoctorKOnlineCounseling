package com.doctork.doctorkonlinecounseling.database.mappers;

import com.doctork.doctorkonlinecounseling.database.entities.Physician.PhysicianEntity;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PhysicianEntityMapper {
    @Mapping(target = "user",ignore = true)
    PhysicianEntity modelToEntity(com.doctork.doctorkonlinecounseling.domain.physician.Physician physician);

    @Mapping(target = "user",ignore = true)
    @Named("entityToModelWithExpertise")
    com.doctork.doctorkonlinecounseling.domain.physician.Physician entityToModelWithExpertise(PhysicianEntity physicianEntity);

    @Mapping(target = "user",ignore = true)
    @Mappings({@Mapping(target = "expertises",expression = "java(null)")})
    com.doctork.doctorkonlinecounseling.domain.physician.Physician entityToModel(PhysicianEntity physicianEntity);

}
