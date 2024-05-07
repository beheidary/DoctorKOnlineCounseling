package com.doctork.doctorkonlinecounseling.database.mappers;

import com.doctork.doctorkonlinecounseling.database.entities.user.PatientEntity;
import com.doctork.doctorkonlinecounseling.domain.user.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PatientEntityMapper {

    @Mapping(target = "user",ignore = true)
    Patient entityToModel(PatientEntity patientEntity);

    @Mapping(target = "user",ignore = true)
    PatientEntity modelToEntity(Patient patient);

}
