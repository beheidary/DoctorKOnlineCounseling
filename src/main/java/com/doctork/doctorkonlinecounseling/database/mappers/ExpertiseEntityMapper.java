package com.doctork.doctorkonlinecounseling.database.mappers;

import com.doctork.doctorkonlinecounseling.database.entities.Expertise.ExpertiseEntity;
import com.doctork.doctorkonlinecounseling.database.entities.Expertise.RequestedExpertiseMongoEntity;
import com.doctork.doctorkonlinecounseling.domain.Expertise.RequestedExpertise;
import com.doctork.doctorkonlinecounseling.domain.Expertise.TopExpertises;
import com.doctork.doctorkonlinecounseling.domain.Expertise.Expertise;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface ExpertiseEntityMapper {
    @Mappings({@Mapping(target = "physicians",expression = "java(null)")})
    ExpertiseEntity modelToEntity(Expertise expertise);

    Set<ExpertiseEntity> modelToEntity (Set<Expertise> expertise);

    @Named("entityToModelWithDoctor")
    @Mappings({@Mapping(target = "physicians",expression = "java(null)")})
    Expertise entityToModelWithDoctor(ExpertiseEntity expertiseEntity);

    @Mappings({@Mapping(target = "physicians",expression = "java(null)")})
    Expertise entityToModel(ExpertiseEntity expertiseEntity);

    Set<Expertise> entityToModel (Set<ExpertiseEntity> expertiseEntities);

    @Mappings({@Mapping(target = "physicians",expression = "java(null)")})
    TopExpertises topEntityToModel(ExpertiseEntity expertiseEntity);

    RequestedExpertiseMongoEntity mongoModelToEntity (RequestedExpertise requestedExpertise);

    List<RequestedExpertise> mongoEntityToModel (List<RequestedExpertiseMongoEntity> requestedExpertiseMongoEntities);
    RequestedExpertise mongoEntityToModel (RequestedExpertiseMongoEntity requestedExpertiseMongoEntity);



}
