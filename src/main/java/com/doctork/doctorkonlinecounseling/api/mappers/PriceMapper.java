package com.doctork.doctorkonlinecounseling.api.mappers;


import com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.miscellaneous.PriceInputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.miscellaneous.ServicesInputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.miscellaneous.PriceOutputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.miscellaneous.ServicesOutputDto;
import com.doctork.doctorkonlinecounseling.domain.Price.Price;
import com.doctork.doctorkonlinecounseling.domain.Price.Services;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PriceMapper {

    ServicesOutputDto servicesModelToDto(Services services);

    Services servicesDtoToModel(ServicesInputDto servicesInputDto);

    Price priceDtoToModel (PriceInputDto priceInputDto);

    PriceOutputDto priceModelToDto(Price price);
    List<ServicesOutputDto> servicesModelToDto(List<Services> services);

    List<PriceOutputDto> priceModelToDto (List<Price> prices);



}
