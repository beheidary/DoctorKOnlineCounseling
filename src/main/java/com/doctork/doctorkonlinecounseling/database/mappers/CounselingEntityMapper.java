package com.doctork.doctorkonlinecounseling.database.mappers;

import com.doctork.doctorkonlinecounseling.database.entities.Counseling.OnlineCounselingEntity;
import com.doctork.doctorkonlinecounseling.domain.Counseling.OnlineCounseling;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CounselingEntityMapper {

//    @Mappings({@Mapping(target = "physician.expertises",expression = "java(null)")})

    OnlineCounseling  onlineCounselingEntityToModel (OnlineCounselingEntity onlineCounselingEntity);

    OnlineCounselingEntity onlineCounselingModelToEntity(OnlineCounseling onlineCounseling);

}
