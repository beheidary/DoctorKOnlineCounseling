package com.doctork.doctorkonlinecounseling.database.mappers;

import com.doctork.doctorkonlinecounseling.database.entities.CareCenter.*;
import com.doctork.doctorkonlinecounseling.domain.CareCenter.*;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CareCenterEntityMapper {
    RequestedCareCenterMongoEntity mongoModelToEntity (RequestedCareCenter requestedCareCenter);

    List<RequestedCareCenter> mongoEntityToModel (List<RequestedCareCenterMongoEntity> requestedCareCenterMongoEntities);
    RequestedCareCenter mongoEntityToModel (RequestedCareCenterMongoEntity requestedCareCenterMongoEntities);

    CareCenterEntity mongoModelToMySqlEntity (RequestedCareCenter requestedCareCenter);

    CareCenter careCenterEntityToModel (CareCenterEntity careCenterEntity);

    RequestedCareCenter sqlModelToMongoModel (CareCenter careCenter);

    List<CareCenterTypes> careCenterTypesEntityToModel (List<CareCenterTypeEntity> careCenterTypeEntities);


    CareCenterEntity careCenterModelToEntity (CareCenter careCenter);

    List<CareCenter> careCenterEntityToModel (List<CareCenterEntity> careCenterEntities);
    CareCenterWithPhysicians careCenterEntityToModelWithPhysicians (CareCenterEntity careCenterEntity);

    CareCenterTypesWithCareCenters careCenterTypeEntityToModelWithCareCenters (CareCenterTypeEntity careCenterTypeEntity);

    Set<PhysicianCareCenter> physicianCareCenterEntityToModel (List<PhysicianCareCenterEntity> physicianCareCenterEntities);
    PhysicianCareCenter physicianCareCenterEntityToModel (PhysicianCareCenterEntity physicianCareCenterEntity);

    List<ProvinceCities> provinceCitiesEntityToModel (List<ProvinceCitiesEntity> provinceCitiesEntities );

}
