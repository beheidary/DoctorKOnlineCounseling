package com.doctork.doctorkonlinecounseling.api.adapters.miscellaneous;

import com.doctork.doctorkonlinecounseling.api.dtos.inputDTOs.miscellaneous.PriceInputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.inputDTOs.miscellaneous.ServicesInputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDTOs.miscellaneous.ArticleOutputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDTOs.miscellaneous.FAQOutputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDTOs.miscellaneous.PriceOutputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDTOs.miscellaneous.ServicesOutputDto;
import com.doctork.doctorkonlinecounseling.domain.SpecificModels.Price;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface MiscellaneousAdapter {

    List<FAQOutputDto> getFaq();

    List<ArticleOutputDto> getAllArticles();

    ServicesOutputDto addServices(ServicesInputDto servicesInputDto);



    PriceOutputDto addPrice(PriceInputDto priceInputDto, Long physicianId , Long servicesId);
    PriceOutputDto editPrice(Long priceId, PriceInputDto priceInputDto);
    List<PriceOutputDto> readPrices(Long physicianId);
    Long deletePrice (Long priceId);

}
