package com.doctork.doctorkonlinecounseling.api.mappers;

import com.doctork.doctorkonlinecounseling.api.dtos.inputDTOs.user.PatientInputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDTOs.user.PatientOutputDto;
import com.doctork.doctorkonlinecounseling.domain.user.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PatientMapper {

    Patient inputToModel(PatientInputDto patientInputDto);

    PatientOutputDto modelToOutput(Patient patient);
}
