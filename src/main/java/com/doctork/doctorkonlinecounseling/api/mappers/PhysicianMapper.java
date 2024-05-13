package com.doctork.doctorkonlinecounseling.api.mappers;


import com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.Physician.PhysicianInputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.physician.PhysicianOutputDto;
import com.doctork.doctorkonlinecounseling.domain.physician.Physician;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PhysicianMapper {

    Physician inputToModel(PhysicianInputDto physicianInputDto);

    PhysicianOutputDto modelToOutput(Physician physician);

}
