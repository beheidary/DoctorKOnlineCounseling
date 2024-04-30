package com.doctork.doctorkonlinecounseling.database.mappers.doctor;

import com.doctork.doctorkonlinecounseling.database.entities.doctor.DoctorEntity;
import com.doctork.doctorkonlinecounseling.domain.doctor.Doctor;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DoctorEntityMapper {
    @Mapping(target = "user",ignore = true)
    DoctorEntity modelToEntity(Doctor doctor);

    @Mapping(target = "user",ignore = true)
    @Named("entityToModelWithExpertise")
    Doctor entityToModelWithExpertise(DoctorEntity doctorEntity);

    @Mapping(target = "user",ignore = true)
    @Mappings({@Mapping(target = "expertises",expression = "java(null)")})
    Doctor entityToModel(DoctorEntity doctorEntity);

}
