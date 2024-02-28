package com.doctork.doctorkonlinecounseling.database.mappers.searchEngine;


import com.doctork.doctorkonlinecounseling.database.entities.doctor.DoctorMongoEntity;
import com.doctork.doctorkonlinecounseling.database.entities.searchEngine.ElasticDoctorEntity;
import com.doctork.doctorkonlinecounseling.domain.doctor.Doctor;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ElasticEntityMapper {


    ElasticDoctorEntity mongoToElasticMapper(DoctorMongoEntity doctor);

    Doctor ElasticToDomainMapper(ElasticDoctorEntity doctor);

    List<Doctor> ElasticToDomainMapper(List<ElasticDoctorEntity> doctors);

}
