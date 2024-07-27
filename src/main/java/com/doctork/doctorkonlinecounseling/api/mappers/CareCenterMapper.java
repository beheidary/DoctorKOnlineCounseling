package com.doctork.doctorkonlinecounseling.api.mappers;


import com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.careCenter.RequestedCareCenterInputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.careCenter.RequestedCareCenterOutputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.careCenter.*;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.careCenter.ProvinceCitiesOutputDto;
import com.doctork.doctorkonlinecounseling.domain.CareCenter.*;
import org.bson.types.ObjectId;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CareCenterMapper {

    CareCenterMapper INSTANCE = Mappers.getMapper(CareCenterMapper.class);

    @Mappings({
            @Mapping(target = "_id", source = "_id", qualifiedByName = "objectIdToString")
    })
    List<RequestedCareCenterOutputDto> mongoModelToDto(List<RequestedCareCenter> requestedCareCenters);

    @Mappings({
            @Mapping(target = "_id", source = "_id", qualifiedByName = "objectIdToString")
    })
    RequestedCareCenterOutputDto mongoModelToDto(RequestedCareCenter requestedCareCenter);

    @Mappings({
            @Mapping(target = "_id", source = "_id", qualifiedByName = "stringToObjectId")
    })
    RequestedCareCenter mongoDtoToModel(RequestedCareCenterOutputDto requestedCareCenterOutputDto);

    RequestedCareCenter InputDtoToMongoModel(RequestedCareCenterInputDto requestedCareCenterInputDto);

    List<CareCenterTypesOutputDto> careCenterTypesModelToDto (List<CareCenterTypes> careCenterTypes);

    Set<PhysicianCareCenterOutputDto> physicianCareCenterModelToDto (Set<PhysicianCareCenter> physicianCareCenters);
    PhysicianCareCenterOutputDto physicianCareCenterModelToDto (PhysicianCareCenter physicianCareCenter);

    List<CareCenterOutputDto> carCenterModelToDto (List<CareCenter> careCenters);

    CareCenterTypeOutputWithCareCenters careCenterTypeWithCareCentersModelToDto (CareCenterTypesWithCareCenters careCenters);
    CareCenterOutputDtoWithPhysicians carCenterWithPhysiciansModelToDto (CareCenterWithPhysicians careCenters);

    List<ProvinceCitiesOutputDto> provinceCitiesModelToDto (List<ProvinceCities> provinceCities );


    @Named("objectIdToString")
    default String objectIdToString(ObjectId objectId) {
        return objectId != null ? objectId.toHexString() : null;
    }

    @Named("stringToObjectId")
    default ObjectId stringToObjectId(String id) {
        return id != null ? new ObjectId(id) : null;
    }
}
