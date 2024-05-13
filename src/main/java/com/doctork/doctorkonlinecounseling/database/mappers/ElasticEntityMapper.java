package com.doctork.doctorkonlinecounseling.database.mappers;


import com.doctork.doctorkonlinecounseling.database.entities.Physician.PhysicianMongoEntity;
import com.doctork.doctorkonlinecounseling.database.entities.searchEngine.ElasticPhysicianEntity;
import com.doctork.doctorkonlinecounseling.domain.physician.Physician;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ElasticEntityMapper {


    ElasticPhysicianEntity mongoToElasticMapper(PhysicianMongoEntity doctor);

    Physician ElasticToDomainMapper(ElasticPhysicianEntity doctor);

    List<Physician> ElasticToDomainMapper(List<ElasticPhysicianEntity> physicians);

    ElasticPhysicianEntity DomainToElsticMapper(Physician physician);

}
