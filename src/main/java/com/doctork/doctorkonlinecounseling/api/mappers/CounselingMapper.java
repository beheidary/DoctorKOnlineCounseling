package com.doctork.doctorkonlinecounseling.api.mappers;

import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.Counseling.OnlineCounselingOutputDto;
import com.doctork.doctorkonlinecounseling.domain.Counseling.OnlineCounseling;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CounselingMapper {

    OnlineCounselingOutputDto onlineCounselingModelToOutputDto(OnlineCounseling onlineCounseling);

}
