package com.doctork.doctorkonlinecounseling.api.mappers.Doctor;


import com.doctork.doctorkonlinecounseling.api.dtos.inputDTOs.doctor.DoctorInputDTO;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDTOs.doctor.DoctorOutputDTO;
import com.doctork.doctorkonlinecounseling.domain.doctor.Doctor;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DoctorMapper {

    Doctor inputToModel(DoctorInputDTO doctorInputDTO);

    DoctorOutputDTO modelToOutput(Doctor doctor);

}
