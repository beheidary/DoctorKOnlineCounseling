package com.doctork.doctorkonlinecounseling.api.mappers;

import com.doctork.doctorkonlinecounseling.api.dtos.inputDTOs.miscellaneous.PriceInputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.inputDTOs.miscellaneous.ServicesInputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDTOs.miscellaneous.ArticleOutputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDTOs.miscellaneous.FAQOutputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDTOs.miscellaneous.PriceOutputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDTOs.miscellaneous.ServicesOutputDto;
import com.doctork.doctorkonlinecounseling.domain.SpecificModels.Article;
import com.doctork.doctorkonlinecounseling.domain.SpecificModels.FAQ;
import com.doctork.doctorkonlinecounseling.domain.SpecificModels.Price;
import com.doctork.doctorkonlinecounseling.domain.SpecificModels.Services;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MiscellaneousMapper {

    List<FAQOutputDto> faqModelToDto(List<FAQ> faqs);

    List<ArticleOutputDto> articleModelToDto(List<Article> articles);


    ServicesOutputDto servicesModelToDto(Services services);

    Services servicesDtoToModel(ServicesInputDto servicesInputDto);

    Price priceDtoToModel (PriceInputDto priceInputDto);

    PriceOutputDto priceModelToDto(Price price);

}
