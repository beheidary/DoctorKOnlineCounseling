package com.doctork.doctorkonlinecounseling.database.mappers.doctor;

import com.doctork.doctorkonlinecounseling.database.entities.doctor.ExpertiseEntity;
import com.doctork.doctorkonlinecounseling.domain.doctor.Expertise;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface ExpertiseEntityMapper {

    ExpertiseEntity modelToEntity(Expertise expertise);

    Expertise entityToModel(ExpertiseEntity expertiseEntity);

}
