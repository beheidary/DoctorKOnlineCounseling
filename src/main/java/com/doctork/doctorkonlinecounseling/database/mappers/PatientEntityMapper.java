package com.doctork.doctorkonlinecounseling.database.mappers;

import com.doctork.doctorkonlinecounseling.database.entities.Patient.PatientEntity;
import com.doctork.doctorkonlinecounseling.domain.Patient.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PatientEntityMapper {

    Patient entityToModel(PatientEntity patientEntity);

    PatientEntity modelToEntity(Patient patient);


}
