package com.doctork.doctorkonlinecounseling.api.mappers;

import com.doctork.doctorkonlinecounseling.api.dtos.RequestedExpertiseDto;
import com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.Physician.ExpertiseInputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.SpecificResultDtos.TopExpertisesDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.physician.ExpertiseOutputDto;
import com.doctork.doctorkonlinecounseling.domain.Expertise.RequestedExpertise;
import com.doctork.doctorkonlinecounseling.domain.Expertise.TopExpertises;
import com.doctork.doctorkonlinecounseling.domain.Expertise.Expertise;
import org.bson.types.ObjectId;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ExpertiseMapper {

    ExpertiseMapper INSTANCE = Mappers.getMapper(ExpertiseMapper.class);

    //Expertise inputToModel(ExpertiseInputDto expertiseInputDTO);

    @Mappings({@Mapping(target = "physicians",expression = "java(null)")})
    Expertise inputToModelWithoutDoctor(ExpertiseInputDto expertiseInputDTO);

    ExpertiseOutputDto modelToOutput(Expertise expertise);

    @Mappings({@Mapping(target = "physicians",expression = "java(null)")})
    TopExpertisesDto topModelToOutput(TopExpertises expertise);


    Set<Expertise> inputDtoToModel (Set<ExpertiseInputDto> expertiseInputDtos);

    Set<ExpertiseOutputDto> modelToOutputDto(Set<Expertise> expertise);

    @Mappings({
            @Mapping(target = "_id", source = "_id", qualifiedByName = "objectIdToString")
    })
    List<RequestedExpertiseDto> mongoModelToDto(List<RequestedExpertise> requestedExpertises);

    @Mappings({
            @Mapping(target = "_id", source = "_id", qualifiedByName = "objectIdToString")
    })
    RequestedExpertiseDto mongoModelToDto(RequestedExpertise requestedExpertise);

    @Mappings({
            @Mapping(target = "_id", source = "_id", qualifiedByName = "stringToObjectId")
    })
    RequestedExpertise mongoDtoToModel(RequestedExpertiseDto requestedExpertiseDto);

    @Named("objectIdToString")
    default String objectIdToString(ObjectId objectId) {
        return objectId != null ? objectId.toHexString() : null;
    }

    @Named("stringToObjectId")
    default ObjectId stringToObjectId(String id) {
        return id != null ? new ObjectId(id) : null;
    }

}
