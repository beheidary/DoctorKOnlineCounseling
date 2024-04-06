package com.doctork.doctorkonlinecounseling.database.mappers.doctor;

import com.doctork.doctorkonlinecounseling.database.entities.doctor.DoctorEntity;
import com.doctork.doctorkonlinecounseling.domain.doctor.Doctor;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DoctorEntityMapper {

    DoctorEntity modelToEntity(Doctor doctor);

    @Named("entityToModelWithExpertise")
    Doctor entityToModelWithExpertise(DoctorEntity doctorEntity);

    @Mappings({@Mapping(target = "expertises",expression = "java(null)")})
    Doctor entityToModel(DoctorEntity doctorEntity);




}
