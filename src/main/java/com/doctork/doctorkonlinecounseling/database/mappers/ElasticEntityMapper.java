package com.doctork.doctorkonlinecounseling.database.mappers;


import com.doctork.doctorkonlinecounseling.database.entities.Physician.PhysicianEntity;
import com.doctork.doctorkonlinecounseling.database.entities.searchEngine.ElasticPhysicianEntity;
import com.doctork.doctorkonlinecounseling.database.entities.searchEngine.ElasticPhysicianfakeEntity;
import com.doctork.doctorkonlinecounseling.domain.RabbitMqMessages.CostumeMessage;
import com.doctork.doctorkonlinecounseling.domain.physician.Physician;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ElasticEntityMapper {


    //ElasticPhysicianfakeEntity mongoToElasticMapper(PhysicianMongoEntity doctor);

    Physician ElasticToDomainMapper(ElasticPhysicianfakeEntity doctor);

    List<Physician> ElasticToDomainMapper(List<ElasticPhysicianfakeEntity> physicians);

    ElasticPhysicianfakeEntity DomainToElsticMapper(Physician physician);


    ElasticPhysicianEntity messageToElasticPhysicianMapper(CostumeMessage message);

    PhysicianEntity elasticPhysicianToPhysicianMapper(ElasticPhysicianEntity elasticPhysicianEntity);


}
