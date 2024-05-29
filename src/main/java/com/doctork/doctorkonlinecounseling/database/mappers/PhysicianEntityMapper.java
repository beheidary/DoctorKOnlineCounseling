package com.doctork.doctorkonlinecounseling.database.mappers;

import com.doctork.doctorkonlinecounseling.database.entities.Physician.PhysicianEntity;
import com.doctork.doctorkonlinecounseling.domain.physician.Physician;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PhysicianEntityMapper {

    PhysicianEntity modelToEntity(Physician physician);

    @Named("entityToModelWithExpertise")
    Physician entityToModelWithExpertise(PhysicianEntity physicianEntity);

    @Mappings({@Mapping(target = "expertises",expression = "java(null)")})
    Physician entityToModel(PhysicianEntity physicianEntity);

}
