package com.doctork.doctorkonlinecounseling.api.adapters.Price;

import com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.miscellaneous.PriceInputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.miscellaneous.ServicesInputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.miscellaneous.PriceOutputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.miscellaneous.ServicesOutputDto;

import java.util.List;

public interface PriceAdapter {


    ServicesOutputDto addServices(ServicesInputDto servicesInputDto);
    PriceOutputDto addPrice(PriceInputDto priceInputDto, Long physicianId , Long servicesId);
    PriceOutputDto editPrice(Long priceId, PriceInputDto priceInputDto);
    List<PriceOutputDto> readPrices(Long physicianId);

    Long deletePrice (Long priceId);

}
