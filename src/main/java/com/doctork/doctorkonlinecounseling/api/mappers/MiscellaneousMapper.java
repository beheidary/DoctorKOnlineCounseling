package com.doctork.doctorkonlinecounseling.api.mappers;

import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.PhysicianDetails.ArticleOutputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.miscellaneous.FAQOutputDto;
import com.doctork.doctorkonlinecounseling.domain.PhysicianDetails.Article;
import com.doctork.doctorkonlinecounseling.domain.SpecificModels.FAQ;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MiscellaneousMapper {

    List<FAQOutputDto> faqModelToDto(List<FAQ> faqs);


}
