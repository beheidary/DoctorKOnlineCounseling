package com.doctork.doctorkonlinecounseling.database.mappers.doctor;


import com.doctork.doctorkonlinecounseling.database.entities.doctor.DoctorEntity;
import com.doctork.doctorkonlinecounseling.domain.doctor.Doctor;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DoctorEntityMapper {


    DoctorEntity modelToEntity(Doctor doctor);
    Doctor entityToModel(DoctorEntity doctorEntity);


}

