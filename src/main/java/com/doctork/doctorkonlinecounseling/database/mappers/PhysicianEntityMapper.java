package com.doctork.doctorkonlinecounseling.database.mappers;

import com.doctork.doctorkonlinecounseling.database.entities.Physician.PhysicianEntity;
import com.doctork.doctorkonlinecounseling.domain.physician.Physician;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PhysicianEntityMapper {
    @Mapping(target = "user",ignore = true)
    PhysicianEntity modelToEntity(Physician physician);

    @Mapping(target = "user",ignore = true)
    @Named("entityToModelWithExpertise")
    Physician entityToModelWithExpertise(PhysicianEntity physicianEntity);

    @Mapping(target = "user",ignore = true)
    @Mappings({@Mapping(target = "expertises",expression = "java(null)")})
    Physician entityToModel(PhysicianEntity physicianEntity);

}
